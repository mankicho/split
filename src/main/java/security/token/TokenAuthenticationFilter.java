package security.token;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Component
@Log4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private TokenGeneratorService tokenGeneratorService = new TokenGeneratorServiceImpl();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("tokenAuthenticationFilter intercept request");
        if (isContainToken(httpServletRequest)) {
            System.out.println("토큰있음");
            log.info("http header contains member-token");
            String token = httpServletRequest.getHeader("member-token");
            String userEmail = httpServletRequest.getHeader("member-email");
            if (tokenGeneratorService.getExpiration(token).after(new Date())) {
                log.info("token has not expired");
                MemberToken memberToken = new MemberToken(token, userEmail);
                SecurityContextHolder.getContext().setAuthentication(memberToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    private boolean isContainToken(HttpServletRequest request) {
        String token = request.getHeader("member-token");
        return token != null;
    }
}
