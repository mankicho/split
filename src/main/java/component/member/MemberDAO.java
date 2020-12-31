package component.member;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class MemberDAO {

    @Setter(onMethod_ = {@Autowired})
    private MemberMapper mapper;

    public MemberDTO selects(String email) {
        return mapper.selects(email);
    }

    public int registerMember(MemberDTO memberDTO) {
        return mapper.registerMember(memberDTO);
    }

    public int deleteMember(String email) {
        return mapper.deleteMember(email);
    }

    public String isExistNickname(String nickname) {
        String reVal = mapper.isExistNickname(nickname);
        return reVal == null ? "" : reVal;
    }

    public String isExistEmail(String email) {
        String reVal = mapper.isExistEmail(email);
        return reVal == null ? "" : reVal;
    }

    public int tmpDeleteMember(String email) {
        return mapper.tmpDeleteMember(email);
    }

    public int restoreDeletedMember(String email) {
        return mapper.restoreDeletedMember(email);
    }

    public String isExistPhoneNumber(String pNum) {
        return mapper.isExistPhoneNumber(pNum);
    }
}
