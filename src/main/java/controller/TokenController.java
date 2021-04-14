package controller;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import security.token.TokenGeneratorService;
import security.token.TokenResultView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * for generating, parsing token
 */
@RestController
@RequestMapping(value = "/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenGeneratorService tokenGeneratorService; // Bean 으로 관리를 해야하는가??

    @GetMapping(value = "/get.do")
    public TokenResultView genToken(@RequestParam(value = "subject") String subject) {
        String token = tokenGeneratorService.createToken(subject, 1000 * 60 * 60 * 24 * 31L);
        return new TokenResultView(token);
    }

    @GetMapping("/get/subject")
    public TokenResultView getSubject(@RequestParam("token") String token) {
        String subject = tokenGeneratorService.getSubject(token);
        return new TokenResultView(subject);
    }

    @GetMapping("/qr/auth/get.do")
    public TokenResultView genTokenForQrAuth(@RequestParam(value = "subject") String subject) {
        String token = tokenGeneratorService.createToken(subject, (1000 * 60 * 60 * 24 * 30L));
        return new TokenResultView(token);
    }

}
