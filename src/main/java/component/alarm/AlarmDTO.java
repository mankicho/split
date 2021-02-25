package component.alarm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.json.JSONObject;

import java.util.Map;

@Data
public class AlarmDTO {
    private String toEmail;
    private Map<String,Object> msg;
}
