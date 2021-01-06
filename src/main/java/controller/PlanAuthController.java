package controller;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import security.token.TokenGeneratorService;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

/**
 * for user's authentication of plan
 */
@RestController
@RequestMapping(value = "/split/plan/auth")
public class PlanAuthController {

    @Setter(onMethod_ = {@Autowired})
    private TokenGeneratorService tokenGeneratorService;

    @GetMapping(value = "/check.do")
    public HashMap<String, Object> test(@RequestParam(value = "token") String token) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (tokenGeneratorService.getExpiration(token).after(new Date())) {
            hashMap.put("msg", "인증을 성공했습니다");
            hashMap.put("code", "success");
        } else {
            hashMap.put("msg", "인증을 실패했습니다.");
            hashMap.put("code", "fail.");
        }
        return hashMap;
    }
}
