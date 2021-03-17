package websocket.execute;

import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ChattingProcessStrategy extends DataProcessStrategy {
    private Map<String, WebSocketSession> userMap;

    public void setUserMap(Map<String, WebSocketSession> userMap) {
        this.userMap = userMap;
    }

    @Override
    public void execute(TextMessage tm) {
        // todo 1. message 에서 plan 의 고유 id 식별.
        JSONObject object = new JSONObject(tm.getPayload());
        // todo 2. 전송한 message 를 db 에 저장.

        // todo 3. plan 내의 모든 member 들에게 message 전송.
    }
}
