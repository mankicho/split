package controller;

import component.sms.SMSService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * @param request 회원가입 할 때 핸드폰 번호 인증을 위한 기능
     * @return
     */
    @PostMapping(value = "/reg/receive.do")
    public int sendMessageForReg(HttpServletRequest request) {
        String phoneNumber = request.getParameter("pNum");
        return smsService.sendSMSForReg(phoneNumber);
    }

    /**
     * @param request
     * 회원의 이메일을 찾을 때
     * @return
     */
    @PostMapping(value = "/upt/receive.do")
    public int sendMessageForUpg(HttpServletRequest request) {
        String phoneNumber = request.getParameter("pNum");
        return smsService.sendSMSForFindEmail(phoneNumber);
    }
}
