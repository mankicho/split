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
    private SqlSession sqlSession;

    public MemberDTO selects(String email) {
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        return memberMapper.selects(email);
    }

    public int registerMember(MemberDTO memberDTO) {
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        return memberMapper.registerMember(memberDTO);
    }

    public int deleteMember(String email) {
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        return memberMapper.deleteMember(email);
    }

    public String isExistNickname(String nickname) {
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        String reVal = memberMapper.isExistNickname(nickname);
        return reVal == null ? "" : reVal;
    }

    public String isExistEmail(String email) {
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        String reVal = memberMapper.isExistEmail(email);
        return reVal == null ? "" : reVal;
    }

    public int tmpDeleteMember(String email) {
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        return memberMapper.tmpDeleteMember(email);
    }

    public int restoreDeletedMember(String email) {
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        return memberMapper.restoreDeletedMember(email);
    }

    public String isExistPhoneNumber(String pNum) {
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        return memberMapper.isExistPhoneNumber(pNum);
    }
}
