package controller;

import component.member.MemberDAO;
import component.member.MemberDTO;
import component.member.MemberService;
import component.member.MemberTmpInfoDTO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import security.token.TokenGeneratorService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Random;

/**
 * handle member's service (register, login, find password and so on)
 */
@RestController
@RequestMapping(value = "/member")
@Log4j
public class MemberController {

    /**
     * member service(register, login, find password and so on)
     */
    @Setter(onMethod_ = @Autowired)
    private MemberService memberService;

    /**
     * password encoder
     */
    @Setter(onMethod_ = {@Autowired})
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * for generating token (to use application service)
     */
    @Setter(onMethod_ = {@Autowired})
    private TokenGeneratorService tokenGeneratorService;

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public HashMap<String, String> handleExceptionWhileSQLQuery(Exception e) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("code", "500");
        hashMap.put("error", "데이터베이스 통신 중 에러");
        return hashMap;
    }

    /**
     * @param request member login(normal or tmp password)
     * @return
     */
    @RequestMapping(value = "/login.do")
    public String memberLogin(HttpServletRequest request) {
        String email = request.getParameter("email");
        String pw = request.getParameter("pw");
        String type = request.getParameter("type");
        System.out.println("type = " + type);
        if (type != null) {
            MemberTmpInfoDTO tmpInfoDTO = memberService.selectsByTmpInfo(email);
            if (tmpInfoDTO != null && passwordEncoder.matches(pw, tmpInfoDTO.getPw())) {
                System.out.println(tmpInfoDTO.getEmail() + "," + tmpInfoDTO.getPw());
                return tokenGeneratorService.createToken(email, 1000 * 60 * 60 * 24);
            }
        }
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

    @PostMapping(value = "/update.do")
    public HashMap<String, String> updatePassword(@RequestParam("email") String email, @RequestParam("pw") String pw) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (email == null || pw == null) {
            hashMap.put("code", "400");
            return hashMap;
        }

        String encodedPw = passwordEncoder.encode(pw);
        int updatedRow = memberService.updatePassword(email, encodedPw);

        if (updatedRow == 0) {
            hashMap.put("code", "500");
        } else {
            hashMap.put("code", "200");
        }
        return hashMap;
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

    @PostMapping(value = "/find/email")
    public HashMap<String, String> findEmail(@RequestParam("pNum") String phoneNumber) {
        HashMap<String, String> hashMap = new HashMap<>();

        String email = memberService.findEmail(phoneNumber);
        if (email != null && !email.equals("")) {
            hashMap.put("email", email);
            hashMap.put("status", "202");
        } else {
            hashMap.put("email", "null");
            hashMap.put("status", "400");
        }
        return hashMap;
    }

    @PostMapping(value = "/gen/tmp/pw")
    public HashMap<String, String> generateTmpPassword(@RequestParam("email") String email, @RequestParam("pw") String pw) {
        HashMap<String, String> hashMap = new HashMap<>();

        if (email == null || pw == null) {
            hashMap.put("code", "500");
            return hashMap;
        }
        hashMap.put("valEmail", email);
        hashMap.put("valPw", passwordEncoder.encode(pw));
        hashMap.put("upEmail", email);
        hashMap.put("upPw", passwordEncoder.encode(pw));

        int affectedRow = memberService.generateTmpPassword(hashMap);

        if (affectedRow == 0) {
            hashMap.put("code", "400");
        } else {
            hashMap.put("code", "202");
        }
        return hashMap;
    }

    @PostMapping(value = "/tmp/delete.do")
    public int tmpDeleteMember(@RequestParam("email") String email) {
        return memberService.tmpDeleteMember(email);
    }

    @PostMapping(value = "/tmp/restore.do")
    public int restoreDeletedMember(@RequestParam("email") String email) {
        return memberService.restoreDeletedMember(email);
    }

    @PostMapping(value = "/email/get.do")
    public String isExistEmail(@RequestParam("email") String email) {
        return memberService.isExistEmail(email);
    }

    @PostMapping(value = "/pNum/get.do")
    public String isExistPhoneNumber(@RequestParam("pNum") String pNum) {
        return memberService.isExistPhoneNumber(pNum);
    }

}
