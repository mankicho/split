package security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class MemberToken extends AbstractAuthenticationToken {
    private String principal;
    private Object credentials;

    public MemberToken(String token, String userEmail) {
        super(null);
        this.principal = token;
        this.credentials = userEmail;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
