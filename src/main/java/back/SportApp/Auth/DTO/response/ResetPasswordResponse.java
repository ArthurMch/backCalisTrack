package back.SportApp.Auth.DTO.response;

public class ResetPasswordResponse {
    ResetPasswordStatus status;

    public ResetPasswordStatus getStatus() {
        return status;
    }

    public void setStatus(final ResetPasswordStatus status) {
        this.status = status;
    }

}