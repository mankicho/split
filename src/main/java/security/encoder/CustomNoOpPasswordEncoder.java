package security.encoder;

import lombok.extern.log4j.Log4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log4j
public class CustomNoOpPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        log.warn("before encode : " + charSequence);
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        log.warn("matches: " + charSequence + " : " + s);
        return charSequence.toString().equals(s);
    }
}
