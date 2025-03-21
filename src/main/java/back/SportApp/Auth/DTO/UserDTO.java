package back.SportApp.Auth.DTO;

import back.SportApp.User.Role;

public class UserDTO {

    private String email;

    private Role role;

    public UserDTO(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

