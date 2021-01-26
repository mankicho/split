package controller;

import component.sms.SMSService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.token.TokenGeneratorService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Random;

/**
 * for sending message on phone
 */
@RestController
@RequestMapping(value = "/sms")
public class SMSController {

    @Setter(onMethod_ = {@Autowired})
    private SMSService smsService;

    @Setter(onMethod_ = {@Autowired})
    private TokenGeneratorService tokenGeneratorService;

    /**
     * @param request 회원가입 할 때 핸드폰 번호 인증을 위한 기능
     * @return
     */
    @PostMapping(value = "/reg/receive.do")
    public HashMap<String, String> sendMessageForReg(HttpServletRequest request) {
        HashMap<String, String> hashMap = new HashMap<>();
        String phoneNumber = request.getParameter("pNum");
        hashMap.put("pNum", phoneNumber);
        int code = smsService.sendSMSForReg(phoneNumber);
        if (code > 999) {
            hashMap.put("status", "202");
        } else {
            hashMap.put("status", "500");
        }
        hashMap.put("code", code + "");
        hashMap.put("token",tokenGeneratorService.createToken(code+"",1000*180));
        return hashMap;
    }

    /**
     * @param request 회원의 이메일을 찾을 때
     * @return
     */
    @PostMapping(value = "/upt/receive.do")
    public int sendMessageForUpg(HttpServletRequest request) {
        String phoneNumber = request.getParameter("pNum");
        return smsService.sendSMSForFindEmail(phoneNumber);
    }
}
