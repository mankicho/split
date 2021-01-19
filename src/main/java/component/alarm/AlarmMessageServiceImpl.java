package component.alarm;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import websocket.msg.AlarmMessage;

import java.util.HashMap;
import java.util.List;

@Service
public class AlarmMessageServiceImpl implements AlarmMessageService {

    @Setter(onMethod_ = {@Autowired})
    private AlarmMessageDAO alarmMessageDAO;

    @Override
    public List<AlarmMessage> selectMessages(HashMap<String, Object> hashMap) {
        return alarmMessageDAO.selectMessages(hashMap);
    }

    @Override
    public int updateCheckFlag(int messageId) {
        return alarmMessageDAO.updateCheckFlag(messageId);
    }

    @Override
    public int getMaxSeq(String email) {
        return alarmMessageDAO.getMaxSeq(email);
    }

    @Override
    public int saveMessage(AlarmMessage alarmMessage) {
        return alarmMessageDAO.saveMessage(alarmMessage);
    }

}
