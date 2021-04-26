package firebase;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class FirebaseUtil {
//    @Value("#{path['fcm_local']}")
//    private String fcmPath;

    @Value("#{path['fcm_linux_server']}")
    private String fcmPath;

    public void initializeApp() {

        try (FileInputStream refreshToken = new FileInputStream(fcmPath)) {
            Map<String, Object> auth = new HashMap<String, Object>();
            auth.put("uid", "speck-users");
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseOptions options = new FirebaseOptions.Builder().
                        setCredentials(GoogleCredentials.fromStream(refreshToken)).
                        setDatabaseUrl("https://paidagogos-edac9-default-rtdb.firebaseio.com/").
                        setDatabaseAuthVariableOverride(auth).
                        setStorageBucket("paidagogos-edac9.appspot.com").
                        setDatabaseUrl("https://paidagogos-edac9-default-rtdb.firebaseio.com/").build();
                FirebaseApp.initializeApp(options);
                log.info("firebaseApp initializeApp finish");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Credentials getCredentials() {
        try (FileInputStream refreshToken = new FileInputStream(fcmPath)) {
            return GoogleCredentials.fromStream(refreshToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
