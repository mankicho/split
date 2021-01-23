package component.member;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface MemberMapper {
    MemberVO selects(String email); // 회원조회 (삭제예정)

    int registerMember(MemberDTO memberDTO); // 회원가입

    String isExistEmail(@Param("email") String email);

    String isExistPhoneNumber(@Param("pNum") String pNum);

    int deleteMember(@Param("email") String email);

    String isExistNickname(@Param("nickname") String nickname);

    String getEmailByNickname(@Param("nickname") String nickname);

    int tmpDeleteMember(@Param("email") String email);

    int restoreDeletedMember(@Param("email") String email);

    String findEmail(@Param("pNum") String pNum);

    MemberTmpInfoDTO selectsByTmpInfo(@Param("email") String email);

    int generateTmpPassword(HashMap<String, String> hashMap);

    int updatePassword(@Param("email") String email, @Param("pw") String pw);

    int insertPolicy(HashMap<String, Object> hashMap);

    List<MemberTimerVO> selectTimer(@Param("email") String email);

    int insertTimer(HashMap<String,Object> hashMap);

    int addFriend(@Param("to") String toEmail, @Param("from") String fromEmail);

    int addPoint(HashMap<String, Object> hashMap);


}
