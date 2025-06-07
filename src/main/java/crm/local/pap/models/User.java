//  Uma entidade para representar / identificar os DETALHES de um utilizador no sistema por campos (id, name, role, number, email, pass).
//  Serve para mapear os utilizadores na tabela correspondente (Também em desenvolvimento).

package crm.local.pap.models;

import java.util.Optional;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false, unique = true)
    private String email;
    private String pass;

    public User() {
    }

    public User(Long id, String name, String role, String number, String email, String pass) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.number = number;
        this.email = email;
        this.pass = pass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Optional<User> getRoles() {
        throw new UnsupportedOperationException("Unimplemented method 'getRoles'");
    }

    public void setRole(Set<Role> of) {
        throw new UnsupportedOperationException("Unimplemented method 'setRole'");
    }
}
