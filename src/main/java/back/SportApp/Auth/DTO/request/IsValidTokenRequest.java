package back.SportApp.Auth.DTO.request;

import jakarta.validation.constraints.NotBlank;

public class IsValidTokenRequest {

    @NotBlank
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

}
