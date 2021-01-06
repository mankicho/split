package controller;

import application.ErrorCollector;
import component.mail.MailService;
import component.member.MemberMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Setter(onMethod_ = {@Autowired})
    private MemberMapper memberMapper;

    @Setter(onMethod_ = {@Autowired})
    private MailService mailService;

    @GetMapping(value = "/get/errors/for/debug")
    public String adminPage() {
        String message = ErrorCollector.mailMessage();
        boolean send = mailService.send("오류 발생현황[스플릿]", message, "split@studyplanet.kr", "zkspffh@naver.com");

        if (send) {
            return "access";
        } else {
            return "access denied";
        }
    }
}
