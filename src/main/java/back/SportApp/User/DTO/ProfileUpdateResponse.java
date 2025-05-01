package back.SportApp.User.DTO;

public class ProfileUpdateResponse extends SuccessResponse {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

}