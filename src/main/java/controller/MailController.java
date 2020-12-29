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
import java.util.Random;

@RestController
@RequestMapping(value = "/mail")
public class MailController {

    @Setter(onMethod_ = {@Autowired})
    private MailService mailService;

    @Setter(onMethod_ = {@Autowired})
    private MemberService memberService;

    @PostMapping(value = "/send/code")
    public int sendAuthCode(HttpServletRequest request) {
        String email = request.getParameter("email");
        String recordedEmail = memberService.isExistEmail(email);

        // todo 1. email, phoneNumber 정보로 조회하기
        if (!email.equals(recordedEmail)) {
            return 115; // DB 에 존재하지 않는 email (어플 개발도중 코딩 실수로 예상)
        }

        Enumeration<String> myEnum =request.getHeaderNames();
        while(myEnum.hasMoreElements()){
            String key = myEnum.nextElement();
            System.out.println(key+" " +request.getHeader(key));
        }
        String subject = "[SPLIT] 비밀번호 찾기 코드입니다.";
        String randomMessage = generateSalt();
        String message = randomMessage + "\n 를 입력해주세요";
        boolean send = mailService.send(subject, message, "split@studyplanet.kr", email);

        if (send) {
            return 100; // 성공적으로 메일을 보냄
        } else {
            return 101; // 메일 전송 실패
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
