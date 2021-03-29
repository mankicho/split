package exception;

import exception.error.TokenErrorCode;
import exception.view.DefaultErrorView;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@ControllerAdvice
public class TokenException {

    @ExceptionHandler(JwtException.class)
    public @ResponseBody
    DefaultErrorView handleExpiredJwtException(JwtException e) {
        return new DefaultErrorView(10000);
    }


}
