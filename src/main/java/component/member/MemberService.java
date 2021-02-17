package component.member;

import component.member.friend.FriendAddRequestVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface MemberService {
    MemberVO selects(String email);

    int registerMember(MemberDTO memberDTO);

    int deleteMember(String email);

    String isExistNickname(String nickname);

    String isExistEmail(String email);

    int tmpDeleteMember(String email);

    int restoreDeletedMember(String email);

    String isExistPhoneNumber(String pNum);

    String findEmail(String pNum);

    MemberTmpInfoDTO selectsByTmpInfo(String email);

    int generateTmpPassword(HashMap<String, String> hashMap);

    int updatePassword(String email, String pw);

    int insertPolicy(HashMap<String, Object> hashMap);

    List<MemberTimerVO> selectTimer(String email);

    int insertTimer(HashMap<String, Object> hashMap);

    String getEmailByNickname(String nickname);

    int addFriend(String toEmail, String fromEmail);

    List<FriendAddRequestVO> getFriendAddRequest(String email); // 친구추가 요청 가져오기

    int addPoint(String email, int point);

    int autoLogin(String email);

    int logout(String email);

    boolean checkAutoLogin(String email);

    int insertFriendAddRequest(String from, String to);

    int registerDeviceToken(String email, String type, String deviceToken);

}
