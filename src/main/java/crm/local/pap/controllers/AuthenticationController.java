package crm.local.pap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.local.pap.dtos.JwtAuthResponse;
import crm.local.pap.dtos.LoginDTO;
import crm.local.pap.dtos.SignupDTO;
import crm.local.pap.services.JwtProvider;
import crm.local.pap.services.UserService;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @SuppressWarnings("unused")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService; 
    @Autowired
    private JwtProvider jwtProvider;

    
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthResponse(token));
    }


    @PostMapping("/signup")

    public ResponseEntity<?> registerUser(@RequestBody SignupDTO signupRequest) {

        try {

            userService.registerUser(signupRequest);
            return new ResponseEntity<>("Utilizador criado com sucesso!", HttpStatus.OK);

        } catch (RuntimeException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

}