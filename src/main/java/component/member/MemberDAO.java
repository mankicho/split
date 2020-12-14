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

    public MemberDTO selects(String phoneNumber) {
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        return memberMapper.selects(phoneNumber);
    }

    public int insertMember(MemberDTO memberDTO) {
        return sqlSession.insert("component.member.MemberMapper.insertMember", memberDTO);
    }

    public MemberDTO login(String email, String password) {
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        return memberMapper.login(email, password);
    }

}
