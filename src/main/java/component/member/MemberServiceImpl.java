package component.member;

import lombok.Setter;
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
    public int insertMember(MemberDTO memberDTO) {
        return memberDAO.insertMember(memberDTO);
    }

    @Override
    public MemberDTO login(String email, String password) {
        return memberDAO.login(email, password);
    }
}
