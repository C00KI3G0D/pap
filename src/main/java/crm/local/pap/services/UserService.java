package crm.local.pap.services;




import java.util.HashSet;
import java.util.Optional;
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

    public User registerUser(SignupDTO request) {
        User user = new User();
        user.setFirstName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<Role> userRoles = new HashSet<>();

        if(request.getRoles() != null) {
            for (RoleType roleType : request.getRoles()) {
                Optional<Role> role = roleRepository.findByName(roleType);
                role.ifPresent(userRoles::add);
            }
    
            user.setRoles(userRoles);
        }

        return userRepository.save(user);
    }
}
