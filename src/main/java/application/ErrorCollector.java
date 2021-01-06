package application;

import component.mail.MailService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class ErrorCollector {
    private static List<String> errors = new ArrayList<>();

    public static void collect(String message) {
        errors.add(message);
    }

    public static String mailMessage() {
        StringBuilder mailMessage = new StringBuilder();
        mailMessage.append("----------------------").append("\n");
        errors.forEach(msg -> mailMessage.append(msg).append("\n"));
        mailMessage.append("----------------------");
        errors.clear();
        return mailMessage.toString();
    }
}
