package exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@ControllerAdvice
public class TokenException {

    @ExceptionHandler(ExpiredJwtException.class)
    public @ResponseBody
    HashMap<String, Object> handleExpiredJwtException(Exception e) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("msg", "토큰 기간이 만료됬습니다.");
        return hashMap;
    }

    @ExceptionHandler(SignatureException.class)
    public @ResponseBody HashMap<String,Object> handleSignatureException(Exception e){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("msg", "유효하지 않은 토큰 값 입니다.");
        return hashMap;
    }

}
