package crm.local.pap.dtos;

import java.util.Set;

import crm.local.pap.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {
    
    public String name;
    public String email;
    public String password;
    public Set<RoleType> roles;

}
