package component.alarm;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Builder
@Getter
@ToString
public class AlarmDTO {
    private String toEmail;
    private Map<String,Object> msg;
}
