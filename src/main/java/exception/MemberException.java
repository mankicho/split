package exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import exception.error.DefaultErrorCode;
import exception.error.MemberErrorCode;
import exception.view.DefaultErrorView;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
@Log4j2
public class MemberException {

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public DefaultErrorView handleNullPointerException() {
        return errorToView(DefaultErrorCode.NullPointer);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public DefaultErrorView handleHttpMediaTypeNotSupportedException() {
        DefaultErrorCode code = DefaultErrorCode.HttpMediaTypeNotSupportedException;
        log.info(code);
        return errorToView(code);
    }

    // json 데이터 형식에 문제가 있음.
    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            JsonMappingException.class
    })
    @ResponseBody
    public DefaultErrorView handleJSONDataFormatException() {
        DefaultErrorCode code = DefaultErrorCode.JSONMapping;
        log.info(code);
        return errorToView(code);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseBody
    public DefaultErrorView handleSQLIntegrityConstraintViolationException() {
        MemberErrorCode code = MemberErrorCode.SQLIntegrityConstraintViolationException;
        log.info(code);
        DefaultErrorView view = new DefaultErrorView();
        view.setMessage(code.getMsg());
        view.setStatus(code.getCode());
        return view;
    }

    private DefaultErrorView errorToView(DefaultErrorCode code) {
        DefaultErrorView view = new DefaultErrorView();
        view.setStatus(code.getCode());
        view.setMessage(code.getMsg());
        return view;
    }


}
