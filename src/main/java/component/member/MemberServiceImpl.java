package component.member;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Setter(onMethod_ = {@Autowired})
    private MemberDAO memberDAO;

    @Override
    public MemberDTO selects(String phoneNumber) {
        return memberDAO.selects(phoneNumber);
    }

    @Override
    public int registerMember(MemberDTO memberDTO) {

        return memberDAO.registerMember(memberDTO);
    }

    @Override
    public MemberDTO login(String email, String password) {
        return memberDAO.login(email, password);
    }

    @Override
    public int insertSalt(String email, String salt) {
        return memberDAO.insertSalt(email, salt);
    }

    @Override
    public String getSalt(String email) {
        return memberDAO.getSalt(email);
    }

    @Override
    public String isExist(String email) {
        return memberDAO.isExist(email);
    }
}
