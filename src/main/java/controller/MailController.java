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
import java.util.Random;

@RestController
@RequestMapping(value = "/mail")
public class MailController {

    @Setter(onMethod_ = {@Autowired})
    private MailService mailService;

    @Setter(onMethod_ = {@Autowired})
    private MemberService memberService;

    @PostMapping(value = "/send/code")
    public String sendAuthCode(HttpServletRequest request) {
        String email = request.getParameter("email");
        String recordedEmail = memberService.isExistEmail(email);

        if (!email.equals(recordedEmail)) {
            return "F";
        }

        String subject = "[SPLIT] 비밀번호 찾기 코드입니다.";
        String randomMessage = generateSalt();
        String message = randomMessage + "\n 를 입력해주세요";
        boolean send = mailService.send(subject, message, "icnogari@studyplanet.kr", email);

        if (send) {
            return randomMessage;
        } else {
            return "F";
        }
    }

    private String generateSalt() {
        Random random = new Random();

        byte[] salt = new byte[5];
        random.nextBytes(salt);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < salt.length; i++) {
            // byte 값을 Hex 값으로 바꾸기.
            sb.append(String.format("%02x", salt[i]));
        }
        return sb.toString();
    }
}
