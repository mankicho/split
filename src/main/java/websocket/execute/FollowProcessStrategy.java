package websocket.execute;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Map;


public class FollowProcessStrategy implements DataProcessStrategy {
    private Map<String, WebSocketSession> userMap;

    public void setUserMap(Map<String, WebSocketSession> userMap) {
        this.userMap = userMap;
    }

    @Override
    public void execute(TextMessage tm) {

    }
}
