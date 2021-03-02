package controller;

import component.member.MemberService;
import component.sms.SMSService;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class SMSController {

    @Setter(onMethod_ = {@Autowired})
    private SMSService smsService;

    @Setter(onMethod_ = {@Autowired})
    private MemberService memberService;

    /**
     * @param request 회원가입 할 때 핸드폰 번호 인증을 위한 기능
     * @return
     */
    @PostMapping(value = "/reg/receive.do")
    public HashMap<String, String> sendMessageForReg(HttpServletRequest request) {
        HashMap<String, String> hashMap = new HashMap<>();
        String phoneNumber = request.getParameter("pNum");

        String pNumInDB = memberService.isExistPhoneNumber(phoneNumber);
        if (pNumInDB != null && pNumInDB.equals(phoneNumber)) {
            hashMap.put("status", "600"); // 이미 존재하는 핸드폰번호
            return hashMap;
        }
        int code = smsService.sendSMSForReg(phoneNumber);
        if (code > 999) {
            hashMap.put("status", "202");
        } else {
            hashMap.put("status", "500");
        }
        hashMap.put("code", code + "");
        return hashMap;
    }

    /**
     * @param request 회원의 이메일을 찾을 때
     * @return
     */
    @PostMapping(value = "/upt/receive.do")
    public int sendMessageForUpg(HttpServletRequest request) {
        String phoneNumber = request.getParameter("pNum");
        log.info(phoneNumber);
        String pNumInDB = memberService.isExistPhoneNumber(phoneNumber);
        System.out.println("pNum = " + pNumInDB);
        if (pNumInDB == null || pNumInDB.equals("")) {
            return -1;
        }
        return smsService.sendSMSForFindEmail(phoneNumber);
    }
}
