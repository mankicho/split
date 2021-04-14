package component.alarm.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DeleteAlarmDTO {
    private String memberEmail;
    private int alarmId;
}
