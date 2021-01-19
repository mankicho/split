package component.alarm;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.apache.ibatis.annotations.Param;
import websocket.msg.AlarmMessage;

import java.util.HashMap;
import java.util.List;

/**
 * SQL about alarm service
 */
public interface AlarmMessageMapper {
    List<AlarmMessage> selectMessages(HashMap<String, Object> hashMap);

    int updateCheckFlag(@Param("messageId") int messageId);

    int getMaxSeq(@Param("email") String email);

    int saveMessage(AlarmMessage alarmMessage);
}
