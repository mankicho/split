package controller;

import component.member.MemberDTO;
import component.member.MemberService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping(value = "/member")
@Log4j
public class MemberController {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Setter(onMethod_ = {@Autowired})
    private MemberService memberService;

    @GetMapping(value = "/get.do")
    public MemberDTO selects(HttpServletRequest request) {
        String phone_number = request.getParameter("phone_number");
        return memberService.selects(phone_number);
    }



    @PostMapping(value = "/insert.do")
    public String insertMember(HttpServletRequest request) {
        String m_name = request.getParameter("name");
        String birthDate = request.getParameter("bDate");
        Date d = null;
        try {
            d = new Date(format.parse(birthDate).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String gender = request.getParameter("gender");
        String job = request.getParameter("job");
        String nickName = request.getParameter("nickName");
        String phoneNumber = request.getParameter("pNum");
        String email = request.getParameter("email");
        MemberDTO memberDTO = new MemberDTO(m_name, d, gender, job, nickName, phoneNumber, email);
        System.out.println(memberDTO);
        int affectedRow = memberService.insertMember(memberDTO);
//
        if (affectedRow == 0) {
            return "success";
        }
        return "fail";
    }
}
