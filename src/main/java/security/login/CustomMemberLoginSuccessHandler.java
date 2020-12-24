package security.login;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import security.token.TokenGeneratorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Log4j
public class CustomMemberLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Setter(onMethod_ = {@Autowired})
    private TokenGeneratorService tokenGeneratorService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.warn("Login success");

        List<String> roleNames = new ArrayList<>();
        authentication.getAuthorities().forEach(authority -> {
            roleNames.add(authority.getAuthority());
        });

        log.warn("ROLE NAMES: " + roleNames);

        if (roleNames.contains("ROLE_MEMBER")) {
            String password = httpServletRequest.getParameter("password");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",tokenGeneratorService.createToken(password, 1000*60*60*24));
            httpServletResponse.getWriter().println(jsonObject);
            return;
        }

        httpServletResponse.sendRedirect("/member/login.do/");
    }

}
