package component.alarm.fri;

import lombok.Data;

@Data
public class FriendAddRequestAlarmDTO {
    private String fromEmail;
    private String toEmail;
    private int msgId;
}
