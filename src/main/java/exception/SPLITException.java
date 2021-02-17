package exception;

import application.ErrorCollector;
import component.mail.MailService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@Log4j
public class SPLITException {

    @ExceptionHandler(Exception.class)
    public void defaultExceptionHandler(Exception e, HttpServletResponse response) throws IOException {
        String message = LocalDateTime.now() + " " + e.getClass().getName() + " : " + e.getMessage();
        ErrorCollector.collect(message);
        log.info("message = " + message);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        e.printStackTrace();
    }
}
