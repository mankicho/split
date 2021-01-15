package security.admin;

import jdk.nashorn.internal.parser.Token;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;
import security.token.TokenGeneratorService;
import security.token.TokenGeneratorServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminFilter extends OncePerRequestFilter {

    @Setter(onMethod_ = {@Autowired})
    private TokenGeneratorService tokenGeneratorService;

    @Value("#{admin_config['dd06pl742']}")
    private String dd06;

    @Value("#{admin_config['de07zx11783']}")
    private String de07;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (isValidAccess(httpServletRequest)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "관리자 권한이 없습니다.");
        }
    }

    private boolean isValidAccess(HttpServletRequest request) {
        if (dd06.equals(tokenGeneratorService.privateGetSubject(request.getHeader("dd06"))) &&
                de07.equals(tokenGeneratorService.privateGetSubject(request.getHeader("de07")))) {
            return true;
        }
        return false;
    }
}
