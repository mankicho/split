package component.member;

import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    MemberDTO selects(String email); // 회원조회 (삭제예정)

    int registerMember(MemberDTO memberDTO); // 회원가입

    String isExistEmail(@Param("email") String email);

    int insertSalt(@Param("email") String email, @Param("salt") String salt); // salt 삽입

    String getSalt(@Param("email") String email);

    int deleteMember(@Param("email") String email);

    String isExistNickname(@Param("nickname") String nickname);

}
