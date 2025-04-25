package back.SportApp.Email;

public interface EmailService {
    void sendLostPasswordEmail(String to, String link);
}
