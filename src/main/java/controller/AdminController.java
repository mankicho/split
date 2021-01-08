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

import java.util.HashMap;
import java.util.Random;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping(value = "/get/errors/for/debug")
    public HashMap<String, String> adminPage() {
        return ErrorCollector.mailMessage();
    }
}
