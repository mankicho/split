package websocket.execute;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

@Log4j2
public class ConcentrateModeOnStrategy extends DataProcessStrategy {
    private List<WebSocketSession> loginUsers;
    private List<String> timers;

    public void setLoginUsers(List<WebSocketSession> loginUsers) {
        this.loginUsers = loginUsers;
    }

    public void setTimerSessions(List<String> timerSessions) {
        this.timers = timerSessions;
    }

    @Override
    public void execute(TextMessage tm) {
        loginUsers.forEach(session -> {
            try {
                session.sendMessage(new TextMessage("{\"size\":\"" + timers.size() + "\"}"));
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        });
    }
}
