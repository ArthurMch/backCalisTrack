package back.SportApp.User.DTO;

import back.SportApp.Auth.DTO.response.ResetPasswordStatus;

public class ResetPasswordResponse {
    ResetPasswordStatus status;

    public ResetPasswordStatus getStatus() {
        return status;
    }

    public void setStatus(final ResetPasswordStatus status) {
        this.status = status;
    }

}