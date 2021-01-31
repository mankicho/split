package component.member;

import component.mail.MailService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class MemberServiceImpl implements MemberService {

    @Setter(onMethod_ = {@Autowired})
    private MemberDAO memberDAO;

    @Setter(onMethod_ = {@Autowired})
    private MailService mailService;

    @Setter(onMethod_ = {@Autowired})
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * @param email for selecting memberDTO
     * @return
     */
    @Override
    public MemberVO selects(String email) {
        return memberDAO.selects(email);
    }

    /**
     * @param memberDTO user's sign in
     * @return
     */
    @Override
    public int registerMember(MemberDTO memberDTO) {

        return memberDAO.registerMember(memberDTO);
    }

    /**
     * @param email user's withdraw
     * @return
     */
    @Override
    public int deleteMember(String email) {
        return memberDAO.deleteMember(email);
    }

    /**
     * @param nickname check whether a nickname exists
     * @return
     */
    @Override
    public String isExistNickname(String nickname) {
        return memberDAO.isExistNickname(nickname);
    }

    /**
     * @param email check whether a user's e-mail exists
     * @return
     */
    @Override
    public String isExistEmail(String email) {
        return memberDAO.isExistEmail(email);
    }

    /**
     * @param pNum check whether a phone_number exists
     * @return
     */
    @Override
    public String isExistPhoneNumber(String pNum) {
        return memberDAO.isExistPhoneNumber(pNum);
    }

    /**
     * @param email if user leave the SPLIT, there is a one-week withdrawal period
     *              Storing data in DB for retention of retraction period data
     * @return
     */
    @Override
    public int tmpDeleteMember(String email) {
        return memberDAO.tmpDeleteMember(email);
    }

    /**
     * @param email if user wants to cancel that leaving SPLIT
     * @return
     */
    @Override
    public int restoreDeletedMember(String email) {
        return memberDAO.restoreDeletedMember(email);
    }

    /**
     * @param pNum find email if users forgot his or her email
     * @return
     */
    @Override
    public String findEmail(String pNum) {
        return memberDAO.findEmail(pNum);
    }

    @Override
    public MemberTmpInfoDTO selectsByTmpInfo(String email) {
        return memberDAO.selectsByTmpInfo(email);
    }

    @Override
    public int generateTmpPassword(HashMap<String, String> hashMap) {
        String subject = "[SPLIT] 비밀번호 찾기 코드입니다.";
        String randomMessage = generateSalt();
        String message = randomMessage + "\n 를 입력해주세요";
        boolean send = mailService.sendToMemberToFindPassword(subject, message, "split@studyplanet.kr", hashMap.get("valEmail"));
        hashMap.put("valPw", passwordEncoder.encode(randomMessage));
        hashMap.put("upPw", passwordEncoder.encode(randomMessage));

        if (send) {
            return memberDAO.generateTmpPassword(hashMap);
        } else {
            return -100;
        }
    }

    @Override
    public int updatePassword(String email, String pw) {
        return memberDAO.updatePassword(email, pw);
    }

    @Override
    public int insertPolicy(HashMap<String, Object> hashMap) {
        return memberDAO.insertPolicy(hashMap);
    }

    @Override
    public List<MemberTimerVO> selectTimer(String email) {
        return memberDAO.selectTimer(email);
    }

    @Override
    public String getEmailByNickname(String nickname) {
        return memberDAO.getEmailByNickname(nickname);
    }

    /**
     * @param toEmail   친구추가 하기
     * @param fromEmail
     * @return
     */
    @Override
    public int addFriend(String toEmail, String fromEmail) {
        return memberDAO.addFriend(toEmail, fromEmail);
    }

    /**
     * @param email 친구추가 요청 가져오기
     * @return
     */
    @Override
    public List<String> getFriendAddRequest(String email) {
        return memberDAO.getFriendAddRequest(email);
    }

    @Override
    public int addPoint(String email, int point) {
        return memberDAO.addPoint(email, point);
    }

    @Override
    public int insertTimer(HashMap<String, Object> hashMap) {
        return memberDAO.insertTimer(hashMap);
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

}

