package back.SportApp.Auth.DTO.request;
import jakarta.validation.constraints.NotBlank;

public class ResetPasswordRequest {

    @NotBlank
    private String token;

    @NotBlank
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

}
