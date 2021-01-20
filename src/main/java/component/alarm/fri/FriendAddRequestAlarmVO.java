package component.alarm.fri;

import lombok.Data;

import java.sql.Date;

@Data
public class FriendAddRequestAlarmVO {
    private int rid;
    private String fromEmail;
    private String toEmail;
    private int msgId;
    private boolean checkFlag;
    private Date transDatetime;
}
