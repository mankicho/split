package security.encoder;

import component.member.MemberMapper;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class CustomSplitMemberPasswordEncoder implements PasswordEncoder {

    @Setter(onMethod_ = @Autowired)
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String encode(CharSequence charSequence) {
        String encoded = bCryptPasswordEncoder.encode(charSequence);
        return bCryptPasswordEncoder.encode(encoded);
    }

    @Override
    public boolean matches(CharSequence plainPassword, String encodePassword) {
        String encoded = bCryptPasswordEncoder.encode(plainPassword);
        return encodePassword.equals(bCryptPasswordEncoder.encode(encoded));
    }



}
