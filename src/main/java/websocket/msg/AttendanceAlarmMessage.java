package websocket.msg;

import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;

/**
 * 어떤 플랜? / 누구? / 몇시?
 * OOO 님이 출석체크를 했습니다.(2021-01-18 09:43:28)
 */
public class AttendanceAlarmMessage extends AlarmMessage {
    String email;
    int planLogId;

    public AttendanceAlarmMessage(JSONObject object) {
        this.type = object.getInt("type");
        this.planLogId = object.getInt("planLogId");
        this.email = object.getString("email");
    }

    public void setCode(int code) {
        this.code = code;
    }
}
