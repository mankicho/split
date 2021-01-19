package component.alarm.pls;

import org.springframework.stereotype.Service;
import websocket.msg.AlarmMessage;

@Service
public class PlanSharingAlarmServiceImpl implements PlanSharingAlarmService {
    @Override
    public int saveMessage(AlarmMessage alarmMessage) {
        return 0;
    }

    @Override
    public int deleteMessage(int messageId, String email) {
        return 0;
    }
}
