package websocket.msg;

import lombok.Data;
import org.json.JSONObject;

/**
 * OOO 님이 플랜에 참여했습니다.(2021-01-18 09:45:21) / OOO 님이 플랜참여를 철회했습니다. (2021-01-18 09:45:38)
 * 어떤 플랜? / 누구? / 몇시? / 참여인지 철회인지?
 */
@Data
public class PlanSharingAlarmMessage extends AlarmMessage {
    private int planLogId;
    private String email;
    private boolean sharingFlag;

    public PlanSharingAlarmMessage(JSONObject object) {
        this.type = object.getInt("type");
        this.planLogId = object.getInt("planLogId");
        this.email = object.getString("email");
        this.sharingFlag = object.getBoolean("sharingFlag");
    }

    public void setCode(int code) {
        this.code = code;
    }
}
