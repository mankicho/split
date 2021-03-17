package exception;

import exception.view.DefaultErrorView;
import exception.error.DefaultErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Log4j2
public class SPLITException {

//    @ExceptionHandler(Exception.class)
//    public void defaultExceptionHandler(Exception e, HttpServletResponse response) throws IOException {
//        String message = LocalDateTime.now() + " " + e.getClass().getName() + " : " + e.getMessage();
//        ErrorCollector.collect(message);
//        log.info("message = " + message);
//        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
//    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public DefaultErrorView handleMissingServletRequestParameterException(Exception e) {
        log.info(e.getMessage());
        DefaultErrorView view = new DefaultErrorView();
        DefaultErrorCode code = DefaultErrorCode.MissingParameterValue;
        view.setMessage(code.getMsg());
        view.setStatus(code.getCode());
        return view;
    }

}
