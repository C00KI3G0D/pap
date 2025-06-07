//  Classe de Objetos para Transferência de dados simples com campos(name, role, number, email, pass).
//  Serve para transferir dados de utilizadores pela api sem expor a entidade diretamente.

package crm.local.pap.dtos;

public class UserDTO {
    public String name;
    public String role;
    public String number;
    public String email;
    public String pass;

    public UserDTO() {
    }

    public UserDTO(String name, String role, String number, String email, String pass) {
        this.name = name;
        this.role = role;
        this.number = number;
        this.email = email;
        this.pass = pass;
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
}
