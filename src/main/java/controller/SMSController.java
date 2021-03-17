package controller;

import component.member.MemberService;
import component.sms.SMSService;
import component.sms.SMSView;
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
    public SMSView sendMessageForReg(HttpServletRequest request) {
        String phoneNumber = request.getParameter("pNum");

        String pNumInDB = memberService.isExistPhoneNumber(phoneNumber);
//        if (pNumInDB != null && pNumInDB.equals(phoneNumber)) {
//            return new SMSView(-1, 500);
//        }
        return smsService.sendSMSForReg(phoneNumber);
    }

    /**
     * @param request 회원의 이메일을 찾을 때
     * @return
     */
    @PostMapping(value = "/upt/receive.do")
    public SMSView sendMessageForUpg(HttpServletRequest request) {
        String phoneNumber = request.getParameter("pNum");
        String pNumInDB = memberService.isExistPhoneNumber(phoneNumber);
//        if (pNumInDB == null || pNumInDB.equals("")) {
//            SMSView view = new SMSView();
//            view.setStatus(500);
//            view.setCode(-1);
//            return view;
//        }
        return smsService.sendSMSForFindEmail(phoneNumber);
    }
}
