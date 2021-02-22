package security.login;

import component.member.MemberMapper;
import component.member.vo.MemberVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j
public class MemberDetailsService implements UserDetailsService {

    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        log.warn("Load User By UserName : " + s);

        MemberVO memberVO = memberMapper.selects(s);

        log.warn("queried by member mapper : " + memberVO);
        return memberVO == null ? null : new CustomMember(memberVO);
    }
}
