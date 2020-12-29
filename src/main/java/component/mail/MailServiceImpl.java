package component.mail;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService {

    @Setter(onMethod_ = {@Autowired})
    private JavaMailSender javaMailSender;

    /**
     * @param subject
     * @param text
     * @param from
     * @param to
     * SPLIT main email -> SPLIT user (for finding user's password)
     * @return
     */
    @Override
    public boolean send(String subject, String text, String from, String to) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject(subject);
            helper.setText(text);
            helper.setFrom(from);
            helper.setTo(to);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
