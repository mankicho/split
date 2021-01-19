package component.alarm.att;

import org.springframework.stereotype.Service;
import websocket.msg.AlarmMessage;
import websocket.msg.AttendanceAlarmMessage;

import java.util.HashMap;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Override
    public List<AttendanceAlarmMessage> selectAttAlarmMessages(HashMap<String, Object> hashMap) {
        return null;
    }

    @Override
    public int saveMessage(AlarmMessage alarmMessage) {
        return 0;
    }

    @Override
    public int deleteMessage(int messageId, String email) {
        return 0;
    }
}
