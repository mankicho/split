package component.alarm;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * unit service about alarm
 */
public interface AlarmMessageService {
    List<AlarmMessageDTO> selectMessages(HashMap<String,Object> hashMap);

    int updateCheckFlag(int messageId);

    int getMaxSeq(String email);

    int saveMessage(AlarmMessageDTO messageDTO) throws MysqlDataTruncation;
}
