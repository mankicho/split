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
        int planId = Integer.parseInt(request.getParameter("planLogId"));
        hashMap.put("token", token);
        hashMap.put("planLogId", planId);
        hashMap.put("planet", planet);
        hashMap.put("authenticateTime", format.format(new Date()));
        hashMap.put("email", email);
        hashMap.put("authenticateFlag", true);

        return planAuthService.planAuth(hashMap);
    }
}
