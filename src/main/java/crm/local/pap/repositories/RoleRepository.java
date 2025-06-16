//  Melhoramos as operações do CRUD.
//  Também serve de método para encontrar um "role" pelo "name"

package crm.local.pap.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crm.local.pap.enums.RoleType;
import crm.local.pap.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
