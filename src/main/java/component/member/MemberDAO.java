package component.member;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Log4j
@Repository
public class MemberDAO {

    @Setter(onMethod_ = {@Autowired})
    private MemberMapper mapper;

    public MemberVO selects(String email) {
        return containSpecial(email) ? null : mapper.selects(email);
    }

    public int registerMember(MemberDTO memberDTO) {
        return mapper.registerMember(memberDTO);
    }

    public int deleteMember(String email) {
        return containSpecial(email) ? -1 : mapper.deleteMember(email);
    }

    public String isExistNickname(String nickname) {
        String reVal = mapper.isExistNickname(nickname);
        return containSpecial(nickname) ? null : reVal == null ? "" : reVal;
    }

    public String isExistEmail(String email) {
        String reVal = mapper.isExistEmail(email);
        return containSpecial(email) ? null : reVal == null ? "" : reVal;
    }

    public int tmpDeleteMember(String email) {
        return containSpecial(email) ? -1 : mapper.tmpDeleteMember(email);
    }

    public int restoreDeletedMember(String email) {
        return containSpecial(email) ? -1 : mapper.restoreDeletedMember(email);
    }

    public String isExistPhoneNumber(String pNum) {
        return containSpecial(pNum) ? null : mapper.isExistPhoneNumber(pNum);
    }

    public String findEmail(String pNum) {
        return containSpecial(pNum) ? null : mapper.findEmail(pNum);
    }

    public MemberTmpInfoDTO selectsByTmpInfo(String email) {
        return containSpecial(email) ? null : mapper.selectsByTmpInfo(email);
    }

    public int generateTmpPassword(HashMap<String, String> hashMap) {
        return mapper.generateTmpPassword(hashMap);
    }

    public int updatePassword(String email, String pw) {
        return (containSpecial(email) || containSpecial(pw)) ? -1 : mapper.updatePassword(email, pw);
    }

    public int insertPolicy(HashMap<String, Object> hashMap) {
        return mapper.insertPolicy(hashMap);
    }

    public List<MemberTimerVO> selectTimer(@Param("email") String email) {
        if (containSpecial(email)) {
            return null;
        }
        return mapper.selectTimer(email);
    }

    public String getEmailByNickname(@Param("nickname") String nickname) {
        return containSpecial(nickname) ? null : mapper.getEmailByNickname(nickname);
    }

    public int addFriend(String toEmail, String fromEmail) {
        return (containSpecial(toEmail) || containSpecial(fromEmail)) ? -1 : mapper.addFriend(toEmail, fromEmail);
    }

    public List<String> getFriendAddRequest(String email) {
        return containSpecial(email) ? null : mapper.getFriendAddRequest(email);
    }

    ; // 친구추가 요청 가져오기

    public int addPoint(@Param("email") String email, @Param("point") int point) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("point", point);
        return containSpecial(email) ? -1 : mapper.addPoint(hashMap);
    }

    public int insertTimer(HashMap<String, Object> hashMap) {
        return mapper.insertTimer(hashMap);
    }

    public int autoLogin(String email) {
        return mapper.autoLogin(email);
    }

    public int logout(String email) {
        return mapper.logout(email);
    }

    private boolean containSpecial(String str) {
        String pattern = "^[ㄱ-ㅎ가-힣a-zA-Z0-9@.]*$";
        return !Pattern.matches(pattern, str);
    }


}
