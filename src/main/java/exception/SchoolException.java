package exception;

import exception.view.DefaultErrorView;
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
    public DefaultErrorView handleParseException(ParseException e) {
        return errorToView(SchoolErrorCode.ParseError);
    }



    private DefaultErrorView errorToView(SchoolErrorCode code){
        DefaultErrorView view = new DefaultErrorView();
        view.setMessage(code.getMsg());
        view.setStatus(code.getStatus());
        return view;
    }
}
