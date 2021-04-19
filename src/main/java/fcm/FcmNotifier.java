package fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import component.member.vo.MemberDeviceVO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j
public class FcmNotifier {

    private final int UPDATE_CONCENTRATION_TIME = 1;
//    @Value("#{path['fcm_local']}")
//    private String fcmPath;

    @Value("#{path['fcm_server']}")
    private String fcmPath;

//    @Value("#{path['fcm_linux_server']}")
//    private String fcmPath;

    public void sendFCM(String tokenId, String title, String content) {
        try (FileInputStream refreshToken = new FileInputStream(fcmPath)) {

            FirebaseOptions options = new FirebaseOptions.Builder().
                    setCredentials(GoogleCredentials.fromStream(refreshToken)).
                    setDatabaseUrl("https://paidagogos-edac9-default-rtdb.firebaseio.com/").build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }


            Message msg = Message.builder().setAndroidConfig(AndroidConfig.builder()
                    .setTtl(3600 * 1000)
                    .setPriority(AndroidConfig.Priority.NORMAL)
                    .setNotification(AndroidNotification.builder()
                            .setTitle(title).setIcon("http://165.246.197.126:8084/resources/logo.png")
                            .setBody(content)
                            .setColor("#f45342").build())
                    .build())
                    .setToken(tokenId)
                    .build();

            String response = FirebaseMessaging.getInstance().send(msg);
            log.info(response);
        } catch (IOException | FirebaseMessagingException e) {
            log.info("IOException : " + e.getMessage());
        }
    }

    public void sendFCMForUpdateConcentrationTime(List<MemberDeviceVO> memberDeviceVOS) {
        try (FileInputStream refreshToken = new FileInputStream(fcmPath)) {

            FirebaseOptions options = new FirebaseOptions.Builder().
                    setCredentials(GoogleCredentials.fromStream(refreshToken)).
                    setDatabaseUrl("https://paidagogos-edac9-default-rtdb.firebaseio.com/").build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            List<Message> msgList = new ArrayList<>();
            List<String> tokenList = memberDeviceVOS.stream().map(MemberDeviceVO::getDeviceToken).collect(Collectors.toList());

            tokenList.forEach(token -> {
                msgList.add(Message.builder().putData("type", UPDATE_CONCENTRATION_TIME + "").setToken(token).build());
            });
            BatchResponse response = FirebaseMessaging.getInstance().sendAll(msgList);
            final StringBuilder sb = new StringBuilder();
            response.getResponses().forEach(sendResponse -> {
                if (sendResponse.isSuccessful()) {
                    sb.append(sendResponse.getMessageId()).append(" / ");
                }
            });
            log.info("send fcm device info -> [" + sb.toString() + "]");
        } catch (IOException | FirebaseMessagingException e) {
            log.info("IOException : " + e.getMessage());
        }
    }
}
