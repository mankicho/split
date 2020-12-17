package component.member;

public interface MemberService {
    MemberDTO selects(String phoneNumber);

    int registerMember(MemberDTO memberDTO);

    MemberDTO login(String email, String password);

    int insertSalt(String email, String salt);

}
