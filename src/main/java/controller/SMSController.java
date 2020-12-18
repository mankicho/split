package controller;

import component.sms.SMSService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/sms")
public class SMSController {

    @Setter(onMethod_ = {@Autowired})
    private SMSService smsService;

    @PostMapping(value = "/receive.do")
    public String sendMessage(HttpServletRequest request) {
        String phoneNumber = request.getParameter("pNum");

        return smsService.sendSMS(phoneNumber);
    }

}
