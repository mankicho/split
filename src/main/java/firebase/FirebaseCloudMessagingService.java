package firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import component.chat.ChattingMessage;
import component.member.MemberMapper;
import component.member.vo.MemberDeviceVO;
import component.school.SchoolMapper;
import component.school.vo.SchoolMembers;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import websocket.MessageType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j
@RequiredArgsConstructor
public class FirebaseCloudMessagingService {

    private final int UPDATE_CONCENTRATION_TIME = 1;
//    @Value("#{path['fcm_local']}")
//    private String fcmPath;

    private final FirebaseUtil firebaseUtil;
    private final SchoolMapper schoolMapper;
    private final MemberMapper memberMapper;

    //    @Value("#{path['fcm_server']}")
//    private String fcmPath;
//
    @Value("#{path['fcm_linux_server']}")
    private String fcmPath;


    public void sendFCM(String tokenId, String title, String content) {
        firebaseUtil.initializeApp();
        try {
            Message msg = Message.builder().setAndroidConfig(AndroidConfig.builder()
                    .setTtl(3600 * 1000)
                    .setPriority(AndroidConfig.Priority.NORMAL)
                    .setNotification(AndroidNotification.builder()
                            .setTitle(title).setIcon("http://165.246.197.126:8084/resources/logo.png")
                            .setBody(content)
                            .setColor("#f45342").build())
                    .build())
                    .setToken(tokenId)
                    .putData("title","this is test title!!!!")
                    .putData("content","this is test content")
                    .build();

            String response = FirebaseMessaging.getInstance().send(msg);
            log.info(response);
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }

    }

    public void sendFCMForUpdateConcentrationTime(List<MemberDeviceVO> memberDeviceVOS) {
        firebaseUtil.initializeApp();
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

    public void sendChattingMessage(ChattingMessage cm) {
        firebaseUtil.initializeApp();

        if (cm.getMessageType() != MessageType.CHATTING || cm.getMemberEmail() == null) {
            return; // 채팅메세지가 아니거나 메세지에 이메일데이터가 없으면
        }

        // 특정 갤럭시, 특정 탐험단의 모든 유저 가져오기
        List<SchoolMembers> schoolMembers = schoolMapper.getMembers(cm.getSchoolId(), cm.getClassId());
        log.info(schoolMembers);
        List<String> tokens = schoolMembers.stream().map(SchoolMembers::getDeviceToken).collect(Collectors.toList());
        String title = "";
        String content;

        if (cm.getNickname() == null) {
            title = memberMapper.getNickname(cm.getMemberEmail());
        }

        if (tokens.isEmpty()) {
            return;
        }
        try {
            String response = "";

            MulticastMessage msg = MulticastMessage.builder().
                    setApnsConfig(ApnsConfig.builder()
                            .setAps(Aps.builder().setBadge(42).build())
                            .build()).
                    setAndroidConfig(AndroidConfig.builder()
                            .setTtl(3600 * 1000)
                            .setPriority(AndroidConfig.Priority.NORMAL)
                            .setNotification(AndroidNotification.builder()
                                    .setTitle(title)
                                    .setBody(cm.getMessage())
                                    .setColor("#f45342").build())
                            .build())
                    .addAllTokens(tokens)
                    .putData("title", title)
                    .putData("content", cm.getMessage())
                    .build();
            BatchResponse batchResponse = FirebaseMessaging.getInstance().sendMulticast(msg);
            log.info(batchResponse);
            batchResponse.getResponses().forEach(log::info);
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }

    }
}
