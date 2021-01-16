package security.token;

import java.util.Date;

public interface TokenGeneratorService {
    String createToken(String subject, long ttlMillis);

    String getSubject(String token);

    Date getExpiration(String token);

    String privateToken(String subject, int month);

    String privateGetSubject(String token);
}
