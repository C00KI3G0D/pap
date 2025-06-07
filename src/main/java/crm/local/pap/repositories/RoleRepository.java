//  Melhoramos as operações do CRUD.
//  Também serve de método para encontrar um "role" pelo "name"

package crm.local.pap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crm.local.pap.models.Role;

@Repository

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
