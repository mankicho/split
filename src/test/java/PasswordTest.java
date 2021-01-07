import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring/mapper/mapper-context.xml",
        "file:src/main/webapp/WEB-INF/spring/mail/mail-context.xml",
        "file:src/main/webapp/WEB-INF/spring/security/security-context.xml"})
@Log4j
public class PasswordTest {

    @Setter(onMethod_={@Autowired})
    private BCryptPasswordEncoder encoder;

    @Test
    public void passwordTest(){
        log.info(encoder);
        log.info(encoder.encode("123456"));
    }
}
