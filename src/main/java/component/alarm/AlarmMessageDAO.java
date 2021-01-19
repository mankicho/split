package component.alarm;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import websocket.msg.AlarmMessage;

import java.util.HashMap;
import java.util.List;

@Repository
@Log4j
public class AlarmMessageDAO {

    @Setter(onMethod_ = {@Autowired})
    private AlarmMessageMapper alarmMessageMapper;

    /**
     * @param hashMap get users' unreceived message
     * @return
     */
    public List<AlarmMessage> selectMessages(HashMap<String, Object> hashMap) {
        return alarmMessageMapper.selectMessages(hashMap);
    }

    /**
     * @param messageId if user read the message, the message's check flag changed (false -> true)
     * @return
     */
    public int updateCheckFlag(int messageId) {
        return alarmMessageMapper.updateCheckFlag(messageId);
    }

    /**
     * @param email the max of message_sequence of users' all messages
     * @return
     */
    public int getMaxSeq(@Param("email") String email) {
        return alarmMessageMapper.getMaxSeq(email);
    }

    /**
     * @param alarmMessage save message into DB
     * @return
     */
    public int saveMessage(AlarmMessage alarmMessage) {
        return alarmMessageMapper.saveMessage(alarmMessage);
    }
}
