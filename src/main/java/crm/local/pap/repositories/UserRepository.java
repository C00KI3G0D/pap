package crm.local.pap.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import crm.local.pap.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, UUID> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
