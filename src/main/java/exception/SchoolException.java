package exception;

import component.school.view.JoinClassResult;
import exception.error.SchoolErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@ControllerAdvice
@Log4j2
public class SchoolException {

    @ExceptionHandler(ParseException.class)
    @ResponseBody
    public JoinClassResult handleParseException(ParseException e) {
        log.info(e.getMessage());
        JoinClassResult result = new JoinClassResult();

        SchoolErrorCode code = SchoolErrorCode.ParseError;
        result.setStatus(code.getStatus());
        result.setInsertedRow(-1);
        result.setMessage(code.getMsg());
        return result;
    }
}
