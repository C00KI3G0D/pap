package crm.local.pap.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import crm.local.pap.dtos.SignupDTO;
import crm.local.pap.enums.RoleType;
import crm.local.pap.models.Role;
import crm.local.pap.models.User;
import crm.local.pap.repositories.RoleRepository;
import crm.local.pap.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(SignupDTO signupRequest) {

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Error: Email já está vinculado!");
        }

        User user = new User();

        // Mapear os campos do DTO para a entidade User direitim

        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setEmail(signupRequest.getEmail());
        user.setNumber(signupRequest.getNumber());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        // Vamos encontrar o Role de user ou atirar lhe um erro aos cornos se nn encontrar na DB.

        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Default role not found."));

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        return userRepository.save(user);
    }
}