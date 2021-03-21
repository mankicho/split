package exception;

import exception.view.DefaultErrorView;
import exception.error.SchoolErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import view.DefaultResultView;
import view.ResultView;

import java.text.ParseException;

@ControllerAdvice
@Log4j2
public class SchoolException {

    @ExceptionHandler(ParseException.class)
    @ResponseBody
    public ResultView handleParseException(ParseException e) {
        return errorToView(SchoolErrorCode.ParseError);
    }


    private ResultView errorToView(SchoolErrorCode code) {
        DefaultResultView view = new DefaultErrorView();
        view.setMsg(code.getMsg());
        view.setStatus(code.getStatus());
        return view;
    }
}
