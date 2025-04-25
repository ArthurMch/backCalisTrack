package back.SportApp.Auth.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailLocaleCodeRequest {
    @NotBlank
    @Email
    private String email;



    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
