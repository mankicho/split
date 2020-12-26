package controller;

import component.member.MemberDAO;
import component.member.MemberDTO;
import component.member.MemberService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import security.token.TokenGeneratorService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Random;

@RestController
@RequestMapping(value = "/member")
@Log4j
public class MemberController {
    @Setter(onMethod_ = @Autowired)
    private MemberService memberService;

    @Setter(onMethod_ = {@Autowired})
    private BCryptPasswordEncoder passwordEncoder;

    @Setter(onMethod_ = {@Autowired})
    private TokenGeneratorService tokenGeneratorService;

    @RequestMapping(value = "/login.do")
    public String memberLogin(HttpServletRequest request) {
        String email = request.getParameter("email");
        String pw = request.getParameter("pw");

        MemberDTO memberDTO = memberService.selects(email);
        if (memberDTO != null && pw != null && passwordEncoder.matches(pw, memberDTO.getPw())) {
            return tokenGeneratorService.createToken(email, 1000 * 60 * 60 * 24);
        }
        return "fail";
    }

    @PostMapping(value = "/register.do")
    @Transactional(rollbackFor = RuntimeException.class)
    public int insertMember(@RequestBody MemberDTO memberDTO) {
        String pw = memberDTO.getPw();
        // todo 1. pw check(right format?)

        log.info("get parameter memberDTO => " + memberDTO);
        String encodedPassword = passwordEncoder.encode(pw); // salt 와 평문 문자열을 2번 인코딩
        memberDTO.setPw(encodedPassword);

        int affectedRowOfRegisterMember = memberService.registerMember(memberDTO);
        if (affectedRowOfRegisterMember == 1) {
            return 100;
        }
        return 101;
    }

    @PostMapping(value = "/delete.do")
    public int deleteMember(HttpServletRequest request) {
        String email = request.getParameter("email");

        return memberService.deleteMember(email);
    }

    @RequestMapping(value = "/check/nick")
    public String getNickname(HttpServletRequest request) {
        String nickname = request.getParameter("nickname");
        return memberService.isExistNickname(nickname);
    }

}
