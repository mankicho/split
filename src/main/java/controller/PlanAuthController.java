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
import java.util.HashMap;

@RestController
@RequestMapping(value = "/plan/auth")
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
