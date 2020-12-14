package component.member;

public interface MemberService {
    MemberDTO selects(String phoneNumber);

    int insertMember(MemberDTO memberDTO);

    MemberDTO login(String email, String password);
}
