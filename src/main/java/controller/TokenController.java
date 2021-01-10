package controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import security.token.TokenGeneratorService;
import security.token.TokenGeneratorServiceImpl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * for generating, parsing token
 */
@RestController
@RequestMapping(value = "/token")
public class TokenController {

    @Setter(onMethod_ = {@Autowired})
    private TokenGeneratorService tokenGeneratorService; // Bean 으로 관리를 해야하는가??

    @GetMapping(value = "/get.do")
    public Map<String, Object> genToken(@RequestParam(value = "subject") String subject) {
        String token = tokenGeneratorService.createToken(subject, (1000 * 60 * 60 * 24 * 7));
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("result", token);
        return map;
    }

    @GetMapping("/get/subject")
    public Map<String, Object> getSubject(@RequestParam("token") String token) {
        String subject = tokenGeneratorService.getSubject(token);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("result", subject);
        return map;
    }

    @GetMapping("/qr/auth/get.do")
    public Map<String, Object> genTokenForQrAuth(@RequestParam(value = "subject") String subject) {
        String token = tokenGeneratorService.createToken(subject, (1000 * 15));
        Map<String, Object> map = new HashMap<>();
        map.put("result", token);
        return map;
    }

    @GetMapping("/admin/get.do")
    public Map<String, Object> getTokenForAdmin(@RequestParam(value = "subject") String subject) {
        String token = tokenGeneratorService.privateToken(subject, 1000 * 60 * 60 * 24 * 21);
        Map<String, Object> map = new HashMap<>();
        map.put("result", token);
        return map;
    }

    @GetMapping("/admin/get/subject")
    public Map<String, Object> getSubjectForAdmin(@RequestParam("token") String token) {
        String subject = tokenGeneratorService.privateGetSubject(token);
        Map<String, Object> map = new HashMap<>();
        map.put("result", subject);
        return map;
    }

}
