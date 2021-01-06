package exception;

import application.ErrorCollector;
import component.mail.MailService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class SPLITException {

    @ExceptionHandler(Exception.class)
    public void defaultExceptionHandler(Exception e) {
        String message = LocalDateTime.now() + " " + e.getMessage();
        ErrorCollector.collect(message);
    }
}
