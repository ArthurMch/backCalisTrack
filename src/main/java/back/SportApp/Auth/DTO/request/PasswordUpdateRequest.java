package back.SportApp.Auth.DTO.request;

import jakarta.validation.constraints.NotBlank;

public class PasswordUpdateRequest {

    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

}

