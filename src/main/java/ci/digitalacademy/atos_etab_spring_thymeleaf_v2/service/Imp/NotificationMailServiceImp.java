package ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.Imp;

import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.NotificationMailService;
import ci.digitalacademy.atos_etab_spring_thymeleaf_v2.service.dto.MailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class NotificationMailServiceImp implements NotificationMailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendNotificationMail(MailDTO mailDTO) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            helper.setFrom("noreply@delmas-gpt.tech");
            helper.setTo(mailDTO.getEmail());
            helper.setSubject("Vos identifiant de connection");

            String content = "<html>" +
                    "<body>" +
                    "    <div style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #edf2f7; padding: 20px; text-align: center;\">" +
                    "        <div style=\"background-color: #ffffff; width: 100%; max-width: 480px; margin: auto; box-shadow: 0 8px 16px rgba(0,0,0,0.1); border-radius: 10px; overflow: hidden; border-left: 5px solid #4a90e2;\">" +
                    "            <div style=\"background-color: #4a90e2; color: white; padding: 20px; font-size: 18px; text-align: center;\">Vos identifiants de connexion</div>" +
                    "            <div style=\"padding: 20px; color: #333333; line-height: 1.6; text-align: center;\">" +
                    "                Bonjour <strong>" + mailDTO.getUsername() + "</strong>,<br><br>" +
                    "                Voici vos identifiants de connexion pour accéder à votre compte.<br>" +
                    "                <div style=\"font-size: 16px; margin: 20px 0;\">" +
                    "                    <strong>Nom d'utilisateur : </strong>" + mailDTO.getUsername() + "<br>" +
                    "                    <strong>Mot de passe : </strong>" + mailDTO.getPassword() + "<br>" +
                    "                </div>" +
                    "                <br>" +
                    "                Veuillez garder ces informations confidentielles et les utiliser pour vous connecter à votre compte." +
                    "            </div>" +
                    "            <div style=\"background-color: #f7f7f7; color: #666666; text-align: center; padding: 12px 20px; font-size: 14px;\">© 2024 Angaman Cedrick Tous droits réservés.</div>" +
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";



            helper.setText(content, true);
            javaMailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
