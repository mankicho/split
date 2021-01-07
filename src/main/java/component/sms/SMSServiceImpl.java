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
    public int sendSMS(String phoneNumber, String msg, int code) {
        if (phoneNumber.length() < 9 || phoneNumber.length() > 13) {
            return -100;
        }
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

            JSONObject object = new JSONObject();
            object.put("content", msg);
            object.put("type", "SMS");
            object.put("to", Arrays.stream(str).toArray());
            object.put("from", "01036198087");

            OutputStream os = connection.getOutputStream();
            os.write(object.toString().getBytes(StandardCharsets.UTF_8));
            os.flush();
            int status = connection.getResponseCode();

            if (status == 202) {
                return code;
            } else {
                return -1;
            }

        } catch (Exception e) {
            log.error("error occurs while SMS send ==> " + e.getLocalizedMessage());
            return 500;
        }
    }
}
