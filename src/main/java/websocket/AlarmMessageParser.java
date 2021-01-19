package websocket;

import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import websocket.msg.AlarmMessage;
import websocket.msg.AttendanceAlarmMessage;
import websocket.msg.FriendAddRequestAlarmMessage;
import websocket.msg.PlanSharingAlarmMessage;

public class AlarmMessageParser {
    public AlarmMessage parse(TextMessage message) {
        String msg = message.getPayload();
        System.out.println(msg);
        JSONObject object = new JSONObject(msg);
        int type = Integer.parseInt(object.getString("type"));
        return type == 1 ?
                new FriendAddRequestAlarmMessage(object) :
                type == 2 ?
                new AttendanceAlarmMessage(object) : new PlanSharingAlarmMessage(object);
    }
}
