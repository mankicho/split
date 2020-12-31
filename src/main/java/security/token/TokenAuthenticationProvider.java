package security.token;

import component.member.MemberMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;
import java.util.List;

@Log4j
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Setter(onMethod_ = {@Autowired})
    private TokenGeneratorService tokenGeneratorService;

    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MemberToken memberToken = (MemberToken) authentication;
        String token = (String) memberToken.getPrincipal();
        String userEmail = (String) authentication.getCredentials();
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        String searchedEmail = memberMapper.isExistEmail(userEmail);
        if (searchedEmail == null || searchedEmail.equals("")) {
            return null;
        }
        if (userEmail == null || token == null) {
            return null;
        }
        if (userEmail.equals(tokenGeneratorService.getSubject(token))) {
            authentication.setAuthenticated(true);
            return authentication;
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        // 인증객체에서 선언한 객체는 reflection 을 통해서 해당 객체인지 파악하는데 이용된다.
        return MemberToken.class.isAssignableFrom(aClass);
    }
}
