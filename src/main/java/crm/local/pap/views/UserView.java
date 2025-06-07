package crm.local.pap.views;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.local.pap.dtos.UserDTO;
import crm.local.pap.repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserView {

    @Autowired
    private UserRepository userRepository;

    @GetMapping({ "", "/" })
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userRepository.findAll().stream().map(entity -> {
            UserDTO userdto = new UserDTO();
            userdto.name = entity.getName();
            userdto.role = entity.getRole();
            userdto.number = entity.getNumber();
            userdto.email = entity.getEmail();
            userdto.pass = entity.getPass();
            return userdto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }
}
