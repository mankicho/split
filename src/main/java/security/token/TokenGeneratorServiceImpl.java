package security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;

@Service
@PropertySource("classpath:config/admin.properties")
public class TokenGeneratorServiceImpl implements TokenGeneratorService {
    private static final String SECRET_KEY = "asjnejk8213kjbsdvkjbv9knqsdljvbqeuifb298fb3we89vsh89vf3oifnon3fb12";

    @Value("#{admin_config['zcw16ggf2k4v88hb3']}")
    private String zcw16;

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @Override
    public String createToken(String subject, long ttlMillis) {
        if (ttlMillis <= 0) {
            throw new RuntimeException("Expiry time must be greater than Zero : [" + ttlMillis + "] ");
        }

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signedKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setSubject(subject).signWith(signatureAlgorithm, signedKey);
        long nowMillis = System.currentTimeMillis();
        builder.setExpiration(new Date(nowMillis + ttlMillis));
        return builder.compact();
    }

    @Override
    public String getSubject(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    @Override
    public Date getExpiration(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    @Override
    public String privateToken(String subject, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, month);
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(zcw16);
        Key signedKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setSubject(subject).signWith(signatureAlgorithm, signedKey);
        builder.setExpiration(cal.getTime());
        return builder.compact();
    }

    @Override
    public String privateGetSubject(String token) {
        System.out.println("zcw16" + zcw16);
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(zcw16))
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}