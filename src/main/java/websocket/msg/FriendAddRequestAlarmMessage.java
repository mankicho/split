package websocket.msg;

import lombok.Data;
import org.json.JSONObject;

@Data
public class FriendAddRequestAlarmMessage extends AlarmMessage {
    String fromEmail;
    String toEmail;
    String fromNickname;

    public FriendAddRequestAlarmMessage(JSONObject object) {
        this.type = object.getInt("type");
        this.fromEmail = object.getString("fromEmail");
        this.toEmail = object.getString("toEmail");
        this.fromNickname = object.getString("fromNickname");
        this.dateTime = object.getString("datetime");
    }

    public void setCode(int code) {
        this.code = code;
    }

}
