package component.alarm;

import org.apache.ibatis.annotations.Param;
import websocket.msg.AlarmMessage;

public interface AlarmService {
    int saveMessage(AlarmMessage alarmMessage);

    int deleteMessage(int messageId, String email); // id 와 email 로 메세지 조회 후 맞으면 삭제

}
