package application;

import component.mail.MailService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ErrorCollector {
    private static List<String> errors = new ArrayList<>();

    public static void collect(String message) {
        errors.add(message);
    }

    public static HashMap<String, String> mailMessage() {
        HashMap<String, String> hashMap = new HashMap<>();
        int idx = 1;
        for (String error : errors) {
            hashMap.put((idx++) + "", error);
        }
        errors.clear();
        return hashMap;
    }
}
