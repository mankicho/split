package websocket.execute;

import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

@Log4j
public class AttendanceProcessStrategy implements DataProcessStrategy {
    private Map<String, WebSocketSession> cafeMap;

    public void setCafeMap(Map<String, WebSocketSession> cafeMap) {
        this.cafeMap = cafeMap;
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
            }catch (IOException e){
                log.info(getClass().getName() +" IOException occur!! => " + e.getMessage());
            }
        }
        // todo 2. 해당 플랜의 알림창에 출석했음을 알림. (웹소켓 말고 일반적인 http 통신 이용)

    }
}
