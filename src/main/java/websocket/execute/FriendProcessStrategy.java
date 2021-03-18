package websocket.execute;


import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

@Log4j2
public class FriendProcessStrategy extends DataProcessStrategy {

    private Map<String, WebSocketSession> userMap;

    public void setUserMap(Map<String, WebSocketSession> userMap) {
        this.userMap = userMap;
    }

    @Override
    public void execute(TextMessage tm) {
        JSONObject object = new JSONObject(tm.getPayload());
        String to = object.getString("to");
        // todo 1. 상대가 접속해있으면 메세지 날림
        WebSocketSession toSession = userMap.get(to);
        if (toSession != null) {
            try {
                toSession.sendMessage(tm);
            } catch (IOException e) {
                log.info(getClass().getName() + " IOException occur!! => " + e.getMessage());
            }
        }
    }

}
