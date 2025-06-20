package crm.local.pap.controllers;

import crm.local.pap.dtos.JwtAuthResponse;
import crm.local.pap.dtos.LoginDTO;
import crm.local.pap.dtos.SignupDTO;
import crm.local.pap.services.JwtProvider;
import crm.local.pap.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService; // Sim Tiago, tu foste a anta que tinha erros por ter tocado no
                                    // tab mal sem se aperceber e por o REPOSITORY AQUIII
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

            // Resposta a partir da lógica do userService 
            userService.registerUser(signupRequest);
            return new ResponseEntity<>("Utilizador criado com sucesso!", HttpStatus.OK);

        } catch (RuntimeException e) {

            // Vou apanha erros do Service caso dê mierda
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

}