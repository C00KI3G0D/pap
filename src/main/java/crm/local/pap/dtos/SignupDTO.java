package crm.local.pap.dtos;

import lombok.Data;

@Data
public class SignupDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String number;
    private String password;
}