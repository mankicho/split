package component.member;

import org.apache.ibatis.annotations.Param;

public interface MemberService {
    MemberDTO selects(String phoneNumber);

    int registerMember(MemberDTO memberDTO);

    MemberDTO login(String email, String password);

    int insertSalt(String email, String salt);

    String getSalt(String email);

}
