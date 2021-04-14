package controller;

import component.member.*;
import component.member.dto.MemberDTO;
import component.member.dto.MemberFollowingDTO;
import component.member.dto.MemberTmpInfoDTO;
import component.member.response.MemberServiceStatusResponse;
import component.member.response.RegisterMemberResponse;
import component.member.response.UpdatePasswordResponse;
import component.member.response.enumm.RegisterMemberStatus;
import component.member.vo.*;
import file.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import security.token.TokenGeneratorService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * handle member's service (register, login, find password and so on)
 */
@RestController
@RequestMapping(value = "/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Value("#{path['local_home']}")
    private String home;

    //    @Value("#{path['server_home']}")
//    private String home;

    private final FileUploadService fileUploadService;
    // member service(register, login, find password and so on)
    private final MemberService memberService;
    // password encoder
    private final BCryptPasswordEncoder passwordEncoder; // password encoding function
    // for generating token (to use application service)
    private final TokenGeneratorService tokenGeneratorService; // return authentication token

    // 로그인함수 (이메일, 비밀번호)
    @RequestMapping(value = "/login.do")
    public String memberLogin(@RequestParam("email") String email, @RequestParam("pw") String pw) {
        MemberVO memberVO = memberService.selects(email); // db 에서 회원정보 조회
        if (memberVO != null && pw != null && passwordEncoder.matches(pw, memberVO.getPw())) { // db 에 저장된 정보와 사용자 입력 비밀번호를 인코딩한 정보가 일치하면
            int affectedRow = memberService.autoLogin(email); // 자동로그인
            if (affectedRow >= 1) {
                return tokenGeneratorService.createToken(email, 1000 * 60 * 60 * 24 * 30L);
                // 어플내의 기능을 이용하기위한 토큰 발급
            }
        }
        MemberTmpInfoDTO tmpInfoDTO = memberService.selectsByTmpInfo(email);
        if (tmpInfoDTO != null && passwordEncoder.matches(pw, tmpInfoDTO.getPw())) {
            int affectedRow = memberService.autoLogin(email);
            if (affectedRow >= 1) {
                return tokenGeneratorService.createToken(email, 1000 * 60 * 60 * 24 * 30L); // 유효기간 1달
            }
        }
        return "fail";
    }


    // todo 1. 변경될 로직(회원가입)
    // todo 2. file 이랑 data 를 갖고있는 객체를 만들어서 매핑하라는거잖아.
    // 트랜잭션 구현 필요
    @PostMapping(value = "/registerWithFile.do")
    public RegisterMemberResponse insertMemberWithFile(@RequestParam("memberDTO") String data, @RequestParam("file") MultipartFile multipartFile) {
        int savedFile = fileUploadService.fileUpload(home, multipartFile);
        int registerStatus = memberService.registerMember(data);

        RegisterMemberStatus status = RegisterMemberStatus.getRegisterMemberStatus(registerStatus, savedFile);
        return new RegisterMemberResponse(status);
    }


    // 트랜잭션 구현 필요
    @PostMapping(value = "/register.do")
    public int insertMember(@RequestBody MemberDTO memberDTO) {
        String pw = memberDTO.getPw();
//        // todo 1. pw check(right format?)
        String encodedPassword = passwordEncoder.encode(pw); // pw 인코딩
        memberDTO.setPw(encodedPassword);

        int affectedRowOfRegisterMember = memberService.registerMember(memberDTO); // 회원정보 등록
        if (affectedRowOfRegisterMember == 1) {
            return 100;
        }
        return 101;
    }

    @PostMapping(value = "/update.do")
    public UpdatePasswordResponse updatePassword(@RequestParam("email") String email, @RequestParam("pw") String pw) {
        UpdatePasswordResponse view = new UpdatePasswordResponse();
        if (email == null || pw == null) {
            view.setCode("400");
            return view;
        }

        String encodedPw = passwordEncoder.encode(pw);
        int updatedRow = memberService.updatePassword(email, encodedPw);

        if (updatedRow == 0) {
            view.setCode("500");
        } else {
            view.setCode("200");
        }
        return view;
    }

    // todo 2.
    // email 이 null 인지
    @PostMapping(value = "/delete.do")
    public MemberServiceStatusResponse deleteMember(HttpServletRequest request) {
        MemberServiceStatusResponse memberServiceStatusResponse = new MemberServiceStatusResponse();

        String email = request.getParameter("email");
        // 1이면 삭제, 0이면 삭제 x
        int delete = memberService.deleteMember(email);
        if (delete == 1) {
            memberServiceStatusResponse.setStatus(202);
            memberServiceStatusResponse.setMsg("delete success : delete row[" + delete + "]");
        } else {
            memberServiceStatusResponse.setStatus(500);
            memberServiceStatusResponse.setMsg("delete fail : delete row[" + delete + "]");
        }
        return memberServiceStatusResponse;
    }

    @PostMapping(value = "/check/nick")
    public String getNickname(HttpServletRequest request) {
        String nickname = request.getParameter("nickname");
        return memberService.isExistNickname(nickname);
    }

    @PostMapping(value = "/find/email")
    public MemberEmailAndRegDate findEmail(@RequestParam("pNum") String phoneNumber) {
        return memberService.findEmail(phoneNumber);
    }


    @PostMapping(value = "/timer/get.do")
    public List<MemberTimerVO> getTimers(@RequestParam("token") String token) {
        String email = tokenGeneratorService.getSubject(token);
        return memberService.selectTimer(email);
    }

    @PostMapping(value = "/timer/insert.do")
    public int insertTimer(HttpServletRequest request) {
        String token = request.getParameter("token");
        String email = tokenGeneratorService.getSubject(token);
        String seconds = request.getParameter("sec");
        int sec = Integer.parseInt(seconds);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("sec", sec);

        return memberService.insertTimer(hashMap);
    }

    @PostMapping(value = "/gen/tmp/pw") // 비밀번호 찾기
    public MemberEmailAndRegDate generateTmpPassword(@RequestParam("email") String email) {
        HashMap<String, String> hashMap = new HashMap<>();
        log.info("start");
        if (email == null) {
            return new MemberEmailAndRegDate(null, null);
        }
        hashMap.put("valEmail", email);
        hashMap.put("upEmail", email);

        int affectedRow = memberService.generateTmpPassword(hashMap);
        log.info("affectedRow = " + affectedRow);
        if (affectedRow == 0) {
            return new MemberEmailAndRegDate(email, null);
        }
        Timestamp regTime = memberService.getRegDate(email);
        log.info("regTime = " + regTime);
        return new MemberEmailAndRegDate(email, regTime);
    }

    @PostMapping(value = "/tmp/delete.do")
    public int tmpDeleteMember(@RequestParam("email") String email) {
        return memberService.tmpDeleteMember(email);
    }

    @PostMapping(value = "/tmp/restore.do")
    public int restoreDeletedMember(@RequestParam("email") String email) {
        return memberService.restoreDeletedMember(email);
    }

    @PostMapping(value = "/email/get.do") // 이메일이 존재하는가
    public String isExistEmail(@RequestParam("email") String email) {
        return memberService.isExistEmail(email);
    }

    @PostMapping(value = "/pNum/get.do") // 핸드폰번호 존재하는가
    public String isExistPhoneNumber(@RequestParam("pNum") String pNum) {
        return memberService.isExistPhoneNumber(pNum);
    }

    @PostMapping(value = "/add/friend") // 친구추가
    public HashMap<String, String> addFriend(@RequestParam("req_nick") String requestNickname, @RequestParam("res_nick") String responseNickname) {
        HashMap<String, String> hashMap = new HashMap<>();
        String reqEmail = memberService.getEmailByNickname(requestNickname);
        String resEmail = memberService.getEmailByNickname(responseNickname);

        int insertedRow = memberService.addFriend(reqEmail, resEmail);

        return hashMap;
    }

    @PostMapping(value = "/insert/friend/add/request") // 친구추가요청 저장하기
    public HashMap<String, Object> insertFriendAddRequest(@RequestParam("from") String from, @RequestParam("to") String to) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String fromEmail = memberService.isExistEmail(from);
        String toEmail = memberService.isExistEmail(to);

        log.info(fromEmail + "," + toEmail);
        if (fromEmail == null || toEmail == null) {
            hashMap.put("status", 400); // 존재하지않는 이메일에대한 친구추가요청 - 회원이 탈퇴 후 친구추가요청을 보낼 수 있음
            return hashMap;
        }
        int insertedRow = memberService.insertFriendAddRequest(from, to);
        if (insertedRow == 0) {
            hashMap.put("status", 500); // DB query 오류 (
        } else {
            hashMap.put("status", 202); // 정상 처리
        }
        return hashMap;
    }

    @PostMapping(value = "/friend/add/request/get.do")
    public List<FriendAddRequestVO> getFriendAddRequest(@RequestParam("to") String to) {
        return memberService.getFriendAddRequest(to);
    }

    // todo . 바꿀예정
    @GetMapping(value = "/phone/auth.do") // 핸드폰 인증번호 유효시간 3분 체크
    public boolean phoneAuthenticate(HttpServletRequest request) {
        String now = request.getParameter("now");
        try {
            Date d = format.parse(now);
            d.setTime(d.getTime() + 1000 * 180);
            log.info(d.toString());
            log.info("now = " + new Date().toString());
            if (d.after(new Date())) {
                log.info("true");
                return true;
            }
            log.info("false");
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping(value = "/logout.do")
    public int logout(@RequestParam("email") String email) {
        int affectedRow = memberService.logout(email);

        if (affectedRow >= 1) { // 로그아웃 요청이 처리되면
            return 202; // 정상처리
        }

        return 500; // 이미 로그아웃 처리 돼있음.
    }

    @PostMapping(value = "/check/auto/login")
    public boolean checkAutoLogin(@RequestParam("token") String token) {
        String email = tokenGeneratorService.getSubject(token);

        return memberService.checkAutoLogin(email);
    }

    @PostMapping(value = "/follow/get.do")
    public List<MemberFollowingDTO> getFollows(@RequestParam("email") String email) {
        return memberService.getFollowers(email);
    }

    // todo 추후 구현예정
    @PostMapping(value = "/follow/insert.do")
    public MemberControllerStatus memberFollow(@RequestBody MemberFollowingDTO memberFollowingVO) {
        log.info(memberFollowingVO);
        try {
            int insertedRow = memberService.memberFollow(memberFollowingVO);
            if (insertedRow == 1) {
                return new MemberControllerStatus(202);
            } else {
                return new MemberControllerStatus(400);
            }
        } catch (DataIntegrityViolationException e) {
            return new MemberControllerStatus(500);
        }
    }

}
