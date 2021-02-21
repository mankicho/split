package component.alarm;

import lombok.Data;

@Data
public class AlarmVO {
    private int alarmId;
    private int alarm_type;
    private String toEmail;
    private String from;
    private String content;
    private boolean readFlag;
    private boolean checkFlag;
}
