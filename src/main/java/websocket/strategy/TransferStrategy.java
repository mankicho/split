package websocket.strategy;

import component.alarm.att.AttendanceService;
import component.alarm.fri.FriendAddRequestAlarmService;
import component.alarm.pls.PlanSharingAlarmService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;
import websocket.msg.AlarmMessage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class TransferStrategy {
    protected WebSocketSession client;
    protected List<WebSocketSession> loginUsers;
    protected Map<String, WebSocketSession> userMap;

    public abstract void transfer() throws IOException;

    public void setClient(WebSocketSession client) {
        this.client = client;
    }

    public void setLoginUsers(List<WebSocketSession> loginUsers) {
        this.loginUsers = loginUsers;
    }

    public void setUserMap(Map<String, WebSocketSession> userMap) {
        this.userMap = userMap;
    }
}
