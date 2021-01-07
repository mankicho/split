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
    private PlanAuthService planAuthService;

    @GetMapping(value = "/check.do")
    public int test(HttpServletRequest request) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String token = request.getHeader("qr-token");
        int planType = Integer.parseInt(request.getParameter("pType"));
        String email = request.getParameter("email");
        hashMap.put("token", token);
        hashMap.put("planType", planType);
        hashMap.put("email", email);
        return planAuthService.planAuth(hashMap);
    }
}
