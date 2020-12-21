package component.member;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    public int insertSalt(String email, String salt) {
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        return memberMapper.insertSalt(email, salt);
    }

    public String getSalt(String email) {
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        return memberMapper.getSalt(email);
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
}
