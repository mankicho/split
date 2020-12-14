package security.login;

import component.member.MemberDTO;
import component.member.MemberMapper;
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

        MemberDTO memberDTO = memberMapper.selects(s);

        log.warn("queried by member mapper : " + memberDTO);
        return memberDTO == null ? null : new CustomMember(memberDTO);
    }
}
