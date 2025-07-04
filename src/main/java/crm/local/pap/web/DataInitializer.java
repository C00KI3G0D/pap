package crm.local.pap.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import crm.local.pap.enums.RoleType;
import crm.local.pap.models.Role;
import crm.local.pap.repositories.RoleRepository;


@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        
        if (roleRepository.findByName(RoleType.ROLE_USER).isEmpty()) {
            roleRepository.save(new Role(RoleType.ROLE_USER));
        }

        if (roleRepository.findByName(RoleType.ROLE_ADMIN).isEmpty()) {
            roleRepository.save(new Role(RoleType.ROLE_ADMIN));
        }
    }
}