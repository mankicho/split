package component.alarm;

import org.apache.ibatis.annotations.Param;

public interface AlarmMapper {
    int saveFriendAddRequest(@Param("from") String fromEmail, @Param("to") String toEmail);
}
