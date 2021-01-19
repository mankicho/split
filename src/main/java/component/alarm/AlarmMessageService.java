package component.alarm;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.apache.ibatis.annotations.Param;
import websocket.msg.AlarmMessage;

import java.util.HashMap;
import java.util.List;

/**
 * unit service about alarm
 */
public interface AlarmMessageService {
    List<AlarmMessage> selectMessages(HashMap<String,Object> hashMap);

    int updateCheckFlag(int messageId);

    int getMaxSeq(String email);

    int saveMessage(AlarmMessage alarmMessage);
}
