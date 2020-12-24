package security.token;

import lombok.extern.log4j.Log4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;
import java.util.List;

@Log4j
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private TokenGeneratorService tokenGeneratorService = new TokenGeneratorServiceImpl();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("authentication processing...");
        MemberToken memberToken = (MemberToken) authentication;
        String token = (String) memberToken.getPrincipal();
        String userEmail = (String) authentication.getCredentials();

        if (userEmail == null || token == null) {
            return authentication;
        }
        if (userEmail.equals(tokenGeneratorService.getSubject(token))) {
            authentication.setAuthenticated(true);
        }

        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        // 인증객체에서 선언한 객체는 reflection 을 통해서 해당 객체인지 파악하는데 이용된다.
        return MemberToken.class.isAssignableFrom(aClass);
    }
}
