package component.alarm;

import lombok.Data;

@Data
public class AlarmDTO {
    private int alarmType;
    private String toEmail;
    private String from;
    private String content;
}
