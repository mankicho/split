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

    // parameter 가 null 값인가
    // service 의 selects 함수에서 결과가 여러개 리턴이 되는가
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

    // email 이 null 인지
    @PostMapping(value = "/delete.do")
    public HashMap<String, String> deleteMember(HttpServletRequest request) {
        HashMap<String, String> hashMap = new HashMap<>();
        String email = request.getParameter("email");
        // 1이면 삭제, 0이면 삭제 x
        int delete = memberService.deleteMember(email);
        if (delete == 1) {
            hashMap.put("test", "1");
        } else {
            hashMap.put("test", "2");
        }
        return hashMap;
    }

    @RequestMapping(value = "/check/nick")
    public String getNickname(HttpServletRequest request) {
        String nickname = request.getParameter("nickname");
        return memberService.isExistNickname(nickname);
    }

    @PostMapping(value = "/tmp/delete.do")
    public int tmpDeleteMember(@RequestParam String email) {
        return memberService.tmpDeleteMember(email);
    }

    @PostMapping(value = "/tmp/restore.do")
    public int restoreDeletedMember(@RequestParam String email) {
        return memberService.restoreDeletedMember(email);
    }

    @PostMapping(value = "/email/get.do")
    public String isExistEmail(@RequestParam String email) {
        return memberService.isExistEmail(email);
    }

    @PostMapping(value = "/pNum/get.do")
    public String isExistPhoneNumber(@RequestParam String pNum) {
        return memberService.isExistPhoneNumber(pNum);
    }

}
