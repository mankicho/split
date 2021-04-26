package websocket.execute;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

@Component
public abstract class DataProcessStrategy {
    public abstract void execute(TextMessage tm);
}
