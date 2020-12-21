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
    public int insertSalt(String email, String salt) {
        return memberDAO.insertSalt(email, salt);
    }

    @Override
    public String getSalt(String email) {
        return memberDAO.getSalt(email);
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
}
