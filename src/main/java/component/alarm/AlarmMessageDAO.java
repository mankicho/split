package component.alarm;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Log4j
public class AlarmMessageDAO {

    @Setter(onMethod_ = {@Autowired})
    private AlarmMessageMapper messageMapper;

    /**
     * @param hashMap get users' unreceived message
     * @return
     */
    public List<AlarmMessageDTO> selectMessages(HashMap<String, Object> hashMap) {
        return messageMapper.selectMessages(hashMap);
    }

    /**
     * @param messageId if user read the message, the message's check flag changed (false -> true)
     * @return
     */
    public int updateCheckFlag(int messageId) {
        return messageMapper.updateCheckFlag(messageId);
    }

    /**
     * @param email the max of message_sequence of users' all messages
     * @return
     */
    public int getMaxSeq(@Param("email") String email) {
        return messageMapper.getMaxSeq(email);
    }

    /**
     * @param messageDTO save message into DB
     * @return
     */
    public int saveMessage(AlarmMessageDTO messageDTO) {
        try {
            return messageMapper.saveMessage(messageDTO);
        } catch (MysqlDataTruncation trunc) {
            log.info(trunc.getMessage());
            return -1;
        }
    }
}
