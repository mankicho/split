package component.sms;

import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

@Service
@Log4j
public class SMSServiceImpl implements SMSService {
    @Override
    public String sendSMS(String phoneNumber) {
        String[] str = new String[]{phoneNumber};
        try {
            URL url = new URL("https://api-sens.ncloud.com/v1/sms/services/ncp:sms:kr:261072792575:split/messages");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-NCP-auth-key", "Bqm3WYwbuiI7ZaxJQs9H");
            connection.setRequestProperty("X-NCP-service-secret", "195c1c4e4b3b4b879b2a5b112d3b24e9");


            String msg = new Random().nextInt(8999) + 1000 + "";

            JSONObject object = new JSONObject();
            object.put("content", "[split 회원가입]\n인증번호 [" + msg + "] 를 입력해주세요");
            object.put("type", "SMS");
            object.put("to", Arrays.stream(str).toArray());
            object.put("from", "01036198087");

            OutputStream os = connection.getOutputStream();
            os.write(object.toString().getBytes(StandardCharsets.UTF_8));
            os.flush();

            int status = connection.getResponseCode();

            if (status == 202) {
                return msg;
            }else{
                return "invalid";
            }
        } catch (Exception e) {
            log.error("error occurs while SMS send ==> " + e.getLocalizedMessage());
            return "server error";
        }
    }
}
