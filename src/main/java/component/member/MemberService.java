package component.member;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

public interface MemberService {
    MemberDTO selects(String email);

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

}
