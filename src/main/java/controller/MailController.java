package controller;

import component.mail.MailService;
import component.member.MemberDAO;
import component.member.MemberService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Random;

/**
 * for sending email to find password
 */
@RestController
@RequestMapping(value = "/split/mail")
public class MailController {

    @Setter(onMethod_ = {@Autowired})
    private MailService mailService;

    /**
     * @param request
     * 이메일 보내기 (패스워드를 찾기 위한 기능)
     * @return
     */
    @PostMapping(value = "/send/code")
    public HashMap<String, String> sendAuthCode(HttpServletRequest request) {
        HashMap<String, String> hashMap = new HashMap<>();
        String email = request.getParameter("email");

        boolean send = mailService.sendToMemberToFindPassword(email);

        if (send) {
            hashMap.put("code", "100");
        } else {
            hashMap.put("code", "500");
        }
        return hashMap;
    }


}
