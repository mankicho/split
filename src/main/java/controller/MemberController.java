package controller;

import component.member.MemberDTO;
import component.member.MemberService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

@RestController
@RequestMapping(value = "/member")
@Log4j
public class MemberController {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Setter(onMethod_ = @Autowired)
    private MemberService memberService;

    @Setter(onMethod_ = {@Autowired})
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/register.do")
    @Transactional
    public String insertMember(@RequestBody MemberDTO memberDTO) {
        String salt = generateSalt();
        String pw = memberDTO.getPw();
        // todo 1. pw check(right format?)

        log.info("get parameter memberDTO => " + memberDTO);
        String encodedPassword = passwordEncoder.encode(pw + salt); // salt 와 평문 문자열을 2번 인코딩
        memberDTO.setPw(encodedPassword);

//        String email = request.getParameter("email");
//        String phoneNumber = request.getParameter("pNum");
//        String sex = request.getParameter("sex");
//        String bornTime = request.getParameter("born_time");
//        MemberDTO memberDTO = new MemberDTO(email, encodedPassword, phoneNumber, sex, bornTime);
        int affectedRowOfRegisterMember = memberService.registerMember(memberDTO);
//
        int affectedRowOfInsertSalt = memberService.insertSalt(memberDTO.getEmail(), salt);
        if (affectedRowOfInsertSalt == 1 && affectedRowOfRegisterMember == 1) {
            return "success";
        }
        return "fail";
    }

    @PostMapping(value = "/get/salt")
    public String getSalt(HttpServletRequest request) {
        String email = request.getParameter("email");

        return memberService.getSalt(email);
    }

    private String generateSalt() {
        Random random = new Random();

        byte[] salt = new byte[8];
        random.nextBytes(salt);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < salt.length; i++) {
            // byte 값을 Hex 값으로 바꾸기.
            sb.append(String.format("%02x", salt[i]));
        }

        return sb.toString();
    }
}
