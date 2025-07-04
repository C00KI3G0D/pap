package crm.local.pap.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import crm.local.pap.enums.RoleType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Role {

    /*@GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;*/

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @jakarta.persistence.Enumerated(value = jakarta.persistence.EnumType.STRING)
    @jakarta.persistence.Column(nullable = false, unique = true)
    private RoleType name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role(RoleType name) {
        this.name = name;
    }
}
