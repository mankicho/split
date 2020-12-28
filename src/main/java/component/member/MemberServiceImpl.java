package component.member;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Setter(onMethod_ = {@Autowired})
    private MemberDAO memberDAO;

    @Setter(onMethod_ = {@Autowired})
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public MemberDTO selects(String email) {
        return memberDAO.selects(email);
    }

    @Override
    public int registerMember(MemberDTO memberDTO) {

        return memberDAO.registerMember(memberDTO);
    }

    @Override
    public int deleteMember(String email) {
        return memberDAO.deleteMember(email);
    }

    @Override
    public String isExistNickname(String nickname) {
        return memberDAO.isExistNickname(nickname);
    }

    @Override
    public String isExistEmail(String email) {
        return memberDAO.isExistEmail(email);
    }

    @Override
    public int tmpDeleteMember(String email) {
        return memberDAO.tmpDeleteMember(email);
    }

    @Override
    public int restoreDeletedMember(String email) {
        return memberDAO.restoreDeletedMember(email);
    }


    @Override
    public String isExistPhoneNumber(String pNum) {
        return memberDAO.isExistPhoneNumber(pNum);
    }
}
