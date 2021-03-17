package websocket.execute;

import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Log4j
public class AttendanceProcessStrategy extends DataProcessStrategy {
    private Map<String, WebSocketSession> cafeMap;
    private Map<String, WebSocketSession> userMap;


    public void setCafeMap(Map<String, WebSocketSession> cafeMap) {
        this.cafeMap = cafeMap;
    }

    public void setUserMap(Map<String, WebSocketSession> userMap) {
        this.userMap = userMap;
    }

    @Override
    public void execute(TextMessage tm) {
        JSONObject object = new JSONObject(tm.getPayload());
        String cafe = object.getString("cafe"); // 메세지에 카페정보(Q01B)

        // todo 1. 카페 어플리케이션에 출석했음을 알림.
        WebSocketSession cafeSession = cafeMap.get(cafe); // Q01B로 세션 찾아내서
        if (cafeSession != null) {
            try {
                cafeSession.sendMessage(tm); // 카페에 메세지 전달
            } catch (IOException e) {
                log.info(getClass().getName() + " IOException occur!! => " + e.getMessage());
            }
        }
        // todo 2. 해당 플랜을 참여하는 유저들에게 알림.

        // todo 3. 내 친구들에게 소식 알림

    }

    private void sendMessageToUser(String email, TextMessage tm) {
        try {
            userMap.get(email).sendMessage(tm);
        } catch (IOException e) {
            log.info(LocalDateTime.now()+" websocket error occur: att->sendMessage->IOException "+e.getMessage());
        }
    }
}
