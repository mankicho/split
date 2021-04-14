package exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import exception.error.MemberErrorCode;
import exception.error.TokenErrorCode;
import exception.view.DefaultErrorView;
import exception.error.DefaultErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;

@ControllerAdvice
@Log4j2
public class SPLITException {

    // 요구하는 파라미터가 안들어왔을때
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public DefaultErrorView handleMissingServletRequestParameterException(Exception e) {
        log.info(e.getMessage());
        DefaultErrorCode code = DefaultErrorCode.MissingParameterValue;
        return new DefaultErrorView(code);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public @ResponseBody
    DefaultErrorView handleIllegalArgumentException() {
        return new DefaultErrorView(DefaultErrorCode.IllegalDataException);
    }

    // null 에러 발생시
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public DefaultErrorView handleNullPointerException(WebRequest webRequest) {
        log.info(webRequest.getDescription(true));
        return errorToView(DefaultErrorCode.NullPointer);
    }

    // 지원하지않는 type 의 media 에러
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

    // SQL 제약사항에 맞지않는 값
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseBody
    public DefaultErrorView handleSQLIntegrityConstraintViolationException(Exception e) {
        log.info(e.getMessage());
        DefaultErrorCode code = DefaultErrorCode.SQLIntegrityConstraintViolationException;
        return new DefaultErrorView(code);
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    @ResponseBody
    public DefaultErrorView handleSQLSyntaxErrorException(SQLSyntaxErrorException e) {
        log.info(e.getMessage());
        DefaultErrorCode code = DefaultErrorCode.SQLSyntaxErrorException;
        return new DefaultErrorView(code);

    }

    // 숫자 변환시 에러(맞지않는 형식)
    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public DefaultErrorView handleNumberFormatException(NumberFormatException e) {
        DefaultErrorCode numberFormatException = DefaultErrorCode.NumberFormatException;
        return new DefaultErrorView(numberFormatException);
    }

    private DefaultErrorView errorToView(DefaultErrorCode code) {
        return new DefaultErrorView(code);
    }
}
