package websocket.execute;

import org.springframework.web.socket.TextMessage;

public interface DataProcessStrategy {
    void execute(TextMessage tm);
}
