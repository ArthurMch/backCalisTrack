package back.SportApp.Email;

import io.micrometer.common.util.StringUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;


@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendLostPasswordEmail(String email, String resetLink) {
        String subject = "Réinitialisation de votre mot de passe - Calistrack";
        String content = """
            Bonjour,

            Nous avons reçu une demande de réinitialisation de votre mot de passe pour votre compte Calistrack.

            Si vous êtes à l'origine de cette demande, veuillez cliquer sur le lien ci-dessous pour définir un nouveau mot de passe :
            
            %s

            Ce lien est valable pour une durée limitée. Si vous n’avez pas demandé de réinitialisation, vous pouvez ignorer cet e-mail. Votre mot de passe actuel restera inchangé.

            Pour toute question ou assistance, n'hésitez pas à contacter notre support.

            L'équipe Calistrack
            """.formatted(resetLink);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }


}
