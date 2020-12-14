package component.member;

public interface MemberMapper {
    MemberDTO selects(String phoneNumber);

    int insertMember(MemberDTO memberDTO);

    boolean isExistsNickName(String nickName);

    MemberDTO login(String email, String password);
}
