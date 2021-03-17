package component.member;

import component.mail.MailService;
import component.member.dto.MemberDTO;
import component.member.dto.MemberFollowingDTO;
import component.member.dto.MemberTmpInfoDTO;
import component.member.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {

    private final MailService mailService;
    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * @param email for selecting memberDTO
     * @return
     */
    public MemberVO selects(String email) {
        return memberMapper.selects(email);
    }

    /**
     * @param memberDTO user's sign in
     * @return
     */
    public int registerMember(MemberDTO memberDTO) {

        return memberMapper.registerMember(memberDTO);
    }

    // todo 1. 바뀔 서비스
    public int registerMember(String data) {

        JSONObject jsonMemberDTO = new JSONObject(data);

        log.info("jsonMemberDTO.toString() = " + jsonMemberDTO.toString());
        String email = jsonMemberDTO.getString("email");
        String pw = jsonMemberDTO.getInt("pw") + "";
        String sex = jsonMemberDTO.getString("sex");
        String bornTime = jsonMemberDTO.getInt("bornTime") + "";
        String phoneNumber = jsonMemberDTO.getString("phoneNumber");
        String nickname = jsonMemberDTO.getString("nickname");

        MemberDTO memberDTO = new MemberDTO(email, pw, phoneNumber, sex, bornTime, nickname);
        log.info("memberDTO = " + memberDTO);
//        // todo 1. pw check(right format?)
        String encodedPassword = passwordEncoder.encode(pw); // pw 인코딩
        memberDTO.setPw(encodedPassword);

        return memberMapper.registerMember(memberDTO);
    }


    /**
     * @param email user's withdraw
     * @return
     */
    public int deleteMember(String email) {
        return memberMapper.deleteMember(email);
    }

    /**
     * @param nickname check whether a nickname exists
     * @return
     */
    public String isExistNickname(String nickname) {
        return memberMapper.isExistNickname(nickname);
    }

    /**
     * @param email check whether a user's e-mail exists
     * @return
     */
    public String isExistEmail(String email) {
        return memberMapper.isExistEmail(email);
    }

    /**
     * @param pNum check whether a phone_number exists
     * @return
     */
    public String isExistPhoneNumber(String pNum) {
        return memberMapper.isExistPhoneNumber(pNum);
    }

    /**
     * @param email if user leave the SPLIT, there is a one-week withdrawal period
     *              Storing data in DB for retention of retraction period data
     * @return
     */
    public int tmpDeleteMember(String email) {
        return memberMapper.tmpDeleteMember(email);
    }

    /**
     * @param email if user wants to cancel that leaving SPLIT
     * @return
     */
    public int restoreDeletedMember(String email) {
        return memberMapper.restoreDeletedMember(email);
    }

    /**
     * @param pNum find email if users forgot his or her email
     * @return
     */
    public MemberEmailAndRegDate findEmail(String pNum) {
        return memberMapper.findEmail(pNum);
    }

    public MemberTmpInfoDTO selectsByTmpInfo(String email) {
        return memberMapper.selectsByTmpInfo(email);
    }

    public int generateTmpPassword(HashMap<String, String> hashMap) {
        String subject = "[SPLIT] 비밀번호 찾기 코드입니다.";
        String randomMessage = generateSalt();
        String message = randomMessage + "\n 를 입력해주세요";
        boolean send = mailService.sendToMemberToFindPassword(subject, message, "split@studyplanet.kr", hashMap.get("valEmail"));
        hashMap.put("valPw", passwordEncoder.encode(randomMessage));
        hashMap.put("upPw", passwordEncoder.encode(randomMessage));

        if (send) {
            return memberMapper.generateTmpPassword(hashMap);
        } else {
            return -100;
        }
    }

    public int updatePassword(String email, String pw) {
        return memberMapper.updatePassword(email, pw);
    }

    public int insertPolicy(HashMap<String, Object> hashMap) {
        return memberMapper.insertPolicy(hashMap);
    }

    public List<MemberTimerVO> selectTimer(String email) {
        return memberMapper.selectTimer(email);
    }

    public String getEmailByNickname(String nickname) {
        return memberMapper.getEmailByNickname(nickname);
    }

    /**
     * @param toEmail   친구추가 하기
     * @param fromEmail
     * @return
     */
    public int addFriend(String toEmail, String fromEmail) {
        return memberMapper.addFriend(toEmail, fromEmail);
    }

    /**
     * @param email 친구추가 요청 가져오기
     * @return
     */
    public List<FriendAddRequestVO> getFriendAddRequest(String email) {
        return memberMapper.getFriendAddRequest(email);
    }

    public int insertFriendAddRequest(String from, String to) {
        return memberMapper.insertFriendAddRequest(from, to);
    }

    public int addPoint(String email, int point) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("point", point);
        return memberMapper.addPoint(hashMap);
    }

    public int insertTimer(HashMap<String, Object> hashMap) {
        return memberMapper.insertTimer(hashMap);
    }

    public int autoLogin(String email) {
        return memberMapper.autoLogin(email);
    }

    public int logout(String email) {
        return memberMapper.logout(email);
    }

    public boolean checkAutoLogin(String email) {
        return memberMapper.checkAutoLogin(email);
    }

    public int registerDeviceToken(String email, String type, String deviceToken) {
        return memberMapper.registerDeviceToken(email, type, deviceToken);
    }

    public List<MemberFollowingDTO> getFollowers(String email) {
        return memberMapper.getFollowers(email);
    }

    public int memberFollow(MemberFollowingDTO memberFollowingVO) {
        return memberMapper.memberFollow(memberFollowingVO);
    }

    public Timestamp getRegDate(String email) {
        return memberMapper.getRegDate(email);
    }

    private String generateSalt() {
        Random random = new Random();

        byte[] salt = new byte[4];
        random.nextBytes(salt);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < salt.length; i++) {
            // byte 값을 Hex 값으로 바꾸기.
            sb.append(String.format("%02x", salt[i]));
        }
        return sb.toString();
    }

    private boolean containSpecial(String str) {
        String pattern = "^[ㄱ-ㅎ가-힣a-zA-Z0-9@.]*$";
        return !Pattern.matches(pattern, str);
    }
}

