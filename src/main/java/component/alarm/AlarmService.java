package component.alarm;

import org.apache.ibatis.annotations.Param;

public interface AlarmService {
    int saveFriendAddRequest(String fromEmail, String toEmail);
}
