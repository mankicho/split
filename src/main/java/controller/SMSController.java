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

@RestController
@RequestMapping(value = "/sms")
public class SMSController {

    @Setter(onMethod_ = {@Autowired})
    private SMSService smsService;

    @PostMapping(value = "/reg/receive.do")
    public int sendMessageForReg(HttpServletRequest request) {
        String phoneNumber = request.getParameter("pNum");

        int code = new Random().nextInt(8999) + 1000;
        String msg = "[split] 회원가입 메세지 입니다. [" + code + "]를 입력해주세요";

        return smsService.sendSMS(phoneNumber, msg, code);
    }

    @PostMapping(value = "/upt/receive.do")
    public int sendMessageForUpg(HttpServletRequest request) {
        String phoneNumber = request.getParameter("pNum");
        int code = new Random().nextInt(8999) + 1000;
        String msg = "[split] 비밀번호를 찾기위한 메세지 입니다. [" + code + "]를 입력해주세요";
        return smsService.sendSMS(phoneNumber, msg, code);
    }

    @PostMapping(value = "/auth/receive.do")
    public int sendMessageForAuth(HttpServletRequest request) {
        String phoneNumber = request.getParameter("pNum");
        int code = new Random().nextInt(8999) + 1000;
        String msg = "[split] 회원가입을 위한 메세지 입니다. [" + code + "]를 입력해주세요";
        return smsService.sendSMS(phoneNumber, msg, code);
    }
}
