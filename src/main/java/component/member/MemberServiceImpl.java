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

    /**
     * @param email for selecting memberDTO
     * @return
     */
    @Override
    public MemberDTO selects(String email) {
        return memberDAO.selects(email);
    }

    /**
     * @param memberDTO user's sign in
     * @return
     */
    @Override
    public int registerMember(MemberDTO memberDTO) {

        return memberDAO.registerMember(memberDTO);
    }

    /**
     * @param email user's withdraw
     * @return
     */
    @Override
    public int deleteMember(String email) {
        return memberDAO.deleteMember(email);
    }

    /**
     * @param nickname check whether a nickname exists
     * @return
     */
    @Override
    public String isExistNickname(String nickname) {
        return memberDAO.isExistNickname(nickname);
    }

    /**
     * @param email check whether a user's e-mail exists
     * @return
     */
    @Override
    public String isExistEmail(String email) {
        return memberDAO.isExistEmail(email);
    }

    /**
     * @param pNum check whether a phone_number exists
     * @return
     */
    @Override
    public String isExistPhoneNumber(String pNum) {
        return memberDAO.isExistPhoneNumber(pNum);
    }

    /**
     * @param email if user leave the SPLIT, there is a one-week withdrawal period
     *              Storing data in DB for retention of retraction period data
     * @return
     */
    @Override
    public int tmpDeleteMember(String email) {
        return memberDAO.tmpDeleteMember(email);
    }

    /**
     * @param email if user wants to cancel that leaving SPLIT
     * @return
     */
    @Override
    public int restoreDeletedMember(String email) {
        return memberDAO.restoreDeletedMember(email);
    }


}
