package fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
@Log4j
public class FcmNotifier {
    public void sendFCM(String tokenId, String title, String content) {
        try {
            FileInputStream refreshToken = new FileInputStream("C:\\Users\\82102\\Desktop\\" +
                    "apache-tomcat-8.5.57-windows-x64\\apache-tomcat-8.5.57\\" +
                    "paidagogos-edac9-firebase-adminsdk-v7k4g-64424beeca.json");

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
                            .setTitle(title)
                            .setBody(content)
                            .setColor("#f45342").build())
                    .build())
                    .setToken(tokenId)
                    .build();

            String response = FirebaseMessaging.getInstance().send(msg);
            log.info("successfully sent message: " + response);
        } catch (IOException | FirebaseMessagingException e) {
            log.info("IOException : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
