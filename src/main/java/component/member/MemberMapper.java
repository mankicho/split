package component.member;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface MemberMapper {
    MemberVO selects(String email); // 회원조회 (삭제예정)

    int registerMember(MemberDTO memberDTO); // 회원가입

    String isExistEmail(@Param("email") String email); // 이메일이 존재하는가

    String isExistPhoneNumber(@Param("pNum") String pNum); // 핸드폰번호가 존재하는가

    int deleteMember(@Param("email") String email); // 회원 탈퇴

    String isExistNickname(@Param("nickname") String nickname); // 닉네임이 존재하는가

    String getEmailByNickname(@Param("nickname") String nickname); // 닉네임으로 이메일조회하기

    int tmpDeleteMember(@Param("email") String email); // 임시탈퇴

    int restoreDeletedMember(@Param("email") String email); // 임시탈퇴 철회

    String findEmail(@Param("pNum") String pNum); // 이메일 찾기

    MemberTmpInfoDTO selectsByTmpInfo(@Param("email") String email); // 임시비밀번호 조회

    int generateTmpPassword(HashMap<String, String> hashMap); // 임시 비밀번호

    int updatePassword(@Param("email") String email, @Param("pw") String pw); // 비밀번호 변경

    int insertPolicy(HashMap<String, Object> hashMap); // 약관

    List<MemberTimerVO> selectTimer(@Param("email") String email); // 집중시간

    int insertTimer(HashMap<String, Object> hashMap); // 집중시간 저장하기

    int addFriend(@Param("to") String toEmail, @Param("from") String fromEmail); // 친구추가하기

    List<String> getFriendAddRequest(@Param("to") String email); // 친구추가 요청 가져오기

    int addPoint(HashMap<String, Object> hashMap);

    int autoLogin(@Param("email") String email);

    int logout(@Param("email") String email);
}
