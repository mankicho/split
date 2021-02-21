package component.alarm;

import lombok.Data;

@Data
public class AlarmDTO {
    private int alarm_type;
    private String toEmail;
    private String from;
    private String content;
}
