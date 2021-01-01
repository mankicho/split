import component.sms.SMSService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {"file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
                "file:src/main/webapp/WEB-INF/spring/mail/mail-context.xml"})
@Log4j
public class SMSTest {

    @Setter(onMethod_ = {@Autowired})
    private SMSService smsService;

    @Test
    public void sendSMS() throws Exception {
        String pNum = "01028470440";

        URL url = new URL("http://studyplanet.kr/sms/upt/receive.do");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        bw.write("pNum=" + pNum);
        bw.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

    }
}
