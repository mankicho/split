package controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import security.token.TokenGeneratorService;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping(value = "/plan/auth")
public class PlanAuthController {

    @Setter(onMethod_ = {@Autowired})
    private TokenGeneratorService tokenGeneratorService;

    @GetMapping(value = "/check.do")
    public String test(@RequestParam(value = "token") String token) {
        if (tokenGeneratorService.getExpiration(token).before(new Date())) {
            return "인증을 성공했습니다.";
        }else{
            return "인증 실패했습니다.";
        }
    }
}
