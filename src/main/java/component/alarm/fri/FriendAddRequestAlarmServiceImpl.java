package component.alarm.fri;

import org.springframework.stereotype.Service;
import websocket.msg.AlarmMessage;

@Service
public class FriendAddRequestAlarmServiceImpl implements FriendAddRequestAlarmService {
    @Override
    public int saveMessage(AlarmMessage alarmMessage) {
        return 0;
    }

    @Override
    public int deleteMessage(int messageId, String email) {
        return 0;
    }
}
