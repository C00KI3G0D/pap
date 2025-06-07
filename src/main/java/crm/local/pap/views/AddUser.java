package crm.local.pap.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.local.pap.dtos.UserDTO;
import crm.local.pap.models.User;
import crm.local.pap.repositories.UserRepository;

@RestController
@RequestMapping("/api/addusers")
public class AddUser {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping({ "", "/" })
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        User user = new User();

        user.setName(userDTO.getName());
        user.setRole(userDTO.getRole());
        user.setNumber(userDTO.getNumber());
        user.setEmail(userDTO.getEmail());
        user.setPass(this.passwordEncoder.encode(userDTO.getPass()));

        userRepository.save(user);

        return ResponseEntity.ok("User added successfully");
    }
}
