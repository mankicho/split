package component.member;

import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    MemberDTO selects(String email);

    int registerMember(MemberDTO memberDTO);

    boolean isExistsNickName(String nickName);

    MemberDTO login(String email, String password);

    int insertSalt(@Param("email") String email, @Param("salt") String salt);
}
