package controller;

import component.plan.auth.PlanAuthService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import security.token.TokenGeneratorService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

/**
 * for user's authentication of plan
 */
@RestController
@RequestMapping(value = "/plan/auth")
public class PlanAuthController {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Setter(onMethod_ = {@Autowired})
    private PlanAuthService planAuthService;
    @Setter(onMethod_ = {@Autowired})
    private TokenGeneratorService tokenGeneratorService;

    @GetMapping(value = "/check.do")
    public int authDo(HttpServletRequest request) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String token = request.getParameter("qr-token");
        String emailToken = request.getParameter("mail-token");
        String planet = tokenGeneratorService.getSubject(token);
        String email = tokenGeneratorService.getSubject(emailToken);
        hashMap.put("token", token); // 토큰 (15 초마다 바뀌는 QR 코드)
        hashMap.put("planet", planet); // 어느곳에서 인증하는지
        hashMap.put("authenticateTime", format.format(new Date())); // 현재시간(인증할 때)
        hashMap.put("email", email); // 이메일
        hashMap.put("authenticateFlag", true);
        hashMap.put("weekday", new Date().getDay());

        return planAuthService.planAuthLog(hashMap);
    }
}
