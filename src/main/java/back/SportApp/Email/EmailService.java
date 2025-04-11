package back.SportApp.Email;

public interface EmailService {
    void sendResetPasswordEmail(String to, String resetToken);
}
