package component.member;

import org.apache.ibatis.annotations.Param;

public interface MemberService {
    MemberDTO selects(String email);

    int registerMember(MemberDTO memberDTO);

    int insertSalt(String email, String salt);

    int deleteMember(String email);

    String isExistNickname(String nickname);

    String isExistEmail(String email);


}
