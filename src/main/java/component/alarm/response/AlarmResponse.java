package component.alarm.response;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class AlarmResponse {
    private int status;
    private String msg;
    private int affectedRow;
}
