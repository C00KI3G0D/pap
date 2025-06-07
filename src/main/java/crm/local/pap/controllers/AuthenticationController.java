package crm.local.pap.controllers;

import crm.local.pap.dtos.LoginDTO;
import crm.local.pap.dtos.SignupDTO;
import crm.local.pap.models.Role;
import crm.local.pap.models.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private crm.local.pap.repositories.UserRepository userRepository;

    @Autowired
    private crm.local.pap.repositories.RoleRepository roleRepository;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginRequest, HttpServletRequest request){
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

        SecurityContextHolder.getContext().setAuthentication(authenticationResponse);

        HttpSession session = request.getSession();
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }



    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupDTO signupRequest, HttpServletRequest request){


        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return new ResponseEntity<>("Email already used!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPass(passwordEncoder.encode(signupRequest.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        user.setRole(Set.of(role));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        System.out.println("logging out");
        request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);


    }
}