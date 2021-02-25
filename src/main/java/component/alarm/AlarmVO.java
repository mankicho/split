package component.alarm;

import lombok.Data;
import org.json.JSONObject;

import java.util.Map;

@Data
public class AlarmVO {
    private int alarmId;
    private String toEmail;
    private Map<String,Object> msg;
    private boolean readFlag;
}
