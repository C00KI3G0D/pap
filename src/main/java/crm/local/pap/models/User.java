package crm.local.pap.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false, unique = true, length = 80)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<Role> roles;

    @OneToMany(
        mappedBy = "responsible",
        cascade = CascadeType.PERSIST,
        fetch = FetchType.LAZY 
    )
    private Set<Task> ownedTasks = new HashSet<>();

    @OneToMany(
        mappedBy = "employee",
        cascade = CascadeType.PERSIST,
        fetch = FetchType.LAZY 
    )
    private Set<Task> assignedTasks = new HashSet<>();

    public void addOwnedTask(Task task) {
        ownedTasks.add(task);
        task.setResponsible(this);
    }
    public void removeOwnedTask(Task task) {
        ownedTasks.remove(task);
        task.setResponsible(this);
    }
    public void addAssignedTask(Task task) {
        assignedTasks.add(task);
        task.setEmployee(this);
    }
    public void removeAssignedTask(Task task) {
        assignedTasks.remove(task);
        task.setEmployee(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for(Role role : this.roles) {
            authorities.add(() -> role.getName().name());
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
    
}
