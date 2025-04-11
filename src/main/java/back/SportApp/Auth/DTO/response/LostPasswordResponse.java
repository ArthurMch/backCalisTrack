package back.SportApp.Auth.DTO.response;

public class LostPasswordResponse {
    boolean success;
    int expirationMinute;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public int getExpirationMinute() {
        return expirationMinute;
    }

    public void setExpirationMinute(final int expirationMinute) {
        this.expirationMinute = expirationMinute;
    }

}

