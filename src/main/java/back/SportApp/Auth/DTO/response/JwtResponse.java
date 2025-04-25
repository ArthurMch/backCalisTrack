package back.SportApp.Auth.DTO.response;

public class JwtResponse {

    private String accessToken;
    private String type = "Bearer";
    private long id;
    private String email;

    public JwtResponse(final String accessToken, final long id, final String email) {
        this.accessToken = accessToken;
        this.id = id;
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
