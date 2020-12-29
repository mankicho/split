package component.alarm;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AlarmMessageServiceImpl implements AlarmMessageService {

    /**
     * DB access object
     */
    @Setter(onMethod_ = {@Autowired})
    private AlarmMessageDAO alarmMessageDAO;

    /**
     *
     * @param hashMap
     * this function brings messages that the user has not read
     * @return
     */
    @Override
    public List<AlarmMessageDTO> selectMessages(HashMap<String,Object> hashMap) {
        return alarmMessageDAO.selectMessages(hashMap);
    }

    /**
     *
     * @param messageId
     * if user reads the message, the message's check flag changed (false -> true)
     * @return
     */
    @Override
    public int updateCheckFlag(int messageId) {
        return alarmMessageDAO.updateCheckFlag(messageId);
    }

    /**
     *
     * @param email
     * this function brings the most recent message send to user
     * @return
     */
    @Override
    public int getMaxSeq(String email) {
        return alarmMessageDAO.getMaxSeq(email);
    }

    /**
     *
     * @param messageDTO
     * save message
     * @return
     */
    @Override
    public int saveMessage(AlarmMessageDTO messageDTO) {
        return alarmMessageDAO.saveMessage(messageDTO);
    }
}
