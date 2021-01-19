package websocket;

import component.alarm.att.AttendanceService;
import component.alarm.fri.FriendAddRequestAlarmService;
import component.alarm.pls.PlanSharingAlarmService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import websocket.msg.AlarmMessage;
import websocket.msg.AttendanceAlarmMessage;
import websocket.msg.FriendAddRequestAlarmMessage;
import websocket.msg.PlanSharingAlarmMessage;
import websocket.strategy.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AlarmMessageServiceExecutor {
    private final int FRIEND_REQUEST = 1;
    private final int ATTENDANCE = 2;
    private final int PLAN_SHARING_IN = 3;
    private final int PLAN_SHARING_OUT = 4;

    private AlarmMessage alarmMessage;
    private WebSocketSession client;
    private List<WebSocketSession> loginUsers;
    private Map<String, WebSocketSession> userMap;
    private TransferStrategy transferStrategy;

    public AlarmMessageServiceExecutor(AlarmMessage alarmMessage, WebSocketSession client,
                                       List<WebSocketSession> loginUsers, Map<String, WebSocketSession> userMap) {
        this.alarmMessage = alarmMessage;
        this.client = client;
        this.loginUsers = loginUsers;
        this.userMap = userMap;
    }

    public void execute() throws IOException {
        switch (alarmMessage.getType()) {
            case FRIEND_REQUEST:
                transferStrategy = new FriendAddTransferStrategy((FriendAddRequestAlarmMessage) alarmMessage);
                break;
            case ATTENDANCE:
                transferStrategy = new AttendanceTransferStrategy((AttendanceAlarmMessage) alarmMessage);
                break;

            case PLAN_SHARING_IN:
                transferStrategy = new PlanSharingInTransferStrategy((PlanSharingAlarmMessage) alarmMessage);
                break;

            case PLAN_SHARING_OUT:
                transferStrategy = new PlanSharingOutTransferStrategy((PlanSharingAlarmMessage) alarmMessage);
                break;
        }
        transferStrategy.setClient(client);
        transferStrategy.setLoginUsers(loginUsers);
        transferStrategy.setUserMap(userMap);
        transferStrategy.transfer();
    }
}
