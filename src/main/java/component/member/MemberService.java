package component.member;

import org.apache.ibatis.annotations.Param;

public interface MemberService {
    MemberDTO selects(String email);

    int registerMember(MemberDTO memberDTO);

    int deleteMember(String email);

    String isExistNickname(String nickname);

    String isExistEmail(String email);

    int tmpDeleteMember(String email);

    int restoreDeletedMember(String email);

    String isExistPhoneNumber(String pNum);

}
