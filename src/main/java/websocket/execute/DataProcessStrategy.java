package websocket.execute;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public abstract class DataProcessStrategy {
    public abstract void execute(TextMessage tm);

    protected void sendMessageIfFail(WebSocketSession socketSession) {
        try {
            socketSession.sendMessage(new TextMessage("fail"));
        } catch (Exception ignored) {
        }
    }
}
