package component.alarm.att;

import component.alarm.AlarmService;
import websocket.msg.AlarmMessage;
import websocket.msg.AttendanceAlarmMessage;

import java.util.HashMap;
import java.util.List;

public interface AttendanceService extends AlarmService {
    List<AttendanceAlarmMessage> selectAttAlarmMessages(HashMap<String, Object> hashMap);

}
