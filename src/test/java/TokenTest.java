import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import security.token.TokenGeneratorService;

import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring/security/security-context.xml",
        "file:src/main/webapp/WEB-INF/spring/mail/mail-context.xml"
})
@Log4j
public class TokenTest {

    @Setter(onMethod_ = {@Autowired})
    private TokenGeneratorService tokenService;

    @Test
    public void TokenTest() {
        log.info(tokenService);
        log.info(tokenService.createToken("ASD", 10000));
    }

    @Test
    public void test() {
        System.out.println(generateSalt());
    }

    private String generateSalt() {
        Random random = new Random();

        byte[] salt = new byte[4];
        random.nextBytes(salt);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < salt.length; i++) {
            // byte 값을 Hex 값으로 바꾸기.
            sb.append(String.format("%02x", salt[i]));
        }
        return sb.toString();
    }

}
