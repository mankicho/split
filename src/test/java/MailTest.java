import component.mail.MailService;
import controller.MailController;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {"file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
                "file:src/main/webapp/WEB-INF/spring/mail/mail-context.xml"})
@Log4j
public class MailTest {

    @Setter(onMethod_ = {@Autowired})
    private MailService mailService;

    private MockMvc mockMvc;

    @Test
    public void mockTest(){
        mockMvc = MockMvcBuilders.standaloneSetup(new MailController()).build();
        log.info(mockMvc);

    }
    @Test
    public void sendTest() throws IOException {
        URL url = new URL("http://localhost:8084/mail/send/code");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("member-token","123");
        connection.setRequestProperty("member-credentials","456");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

        bw.write("email=zkspffh@naver.com");
        bw.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

    @Test
    public void test() {
        mailService.send("ASD", "asd", "", "zkspffh@naver.com");
    }
}
