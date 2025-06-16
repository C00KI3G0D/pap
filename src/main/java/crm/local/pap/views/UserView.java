package crm.local.pap.views;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.local.pap.models.User;
import crm.local.pap.repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserView {

    @Autowired
    private UserRepository userRepository;

    @GetMapping({ "", "/" })
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll().stream().map(entity -> {
            User user = new User();
            user.setFirstName(entity.getFirstName());
            user.setLastName(entity.getLastName());
            user.setRoles(entity.getRoles());
            user.setNumber(entity.getNumber());
            user.setEmail(entity.getEmail());
            user.setPassword(entity.getPassword());
            return user;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }
}
