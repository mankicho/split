package component.alarm.att;

import org.apache.ibatis.annotations.Param;
import websocket.msg.AlarmMessage;
import websocket.msg.AttendanceAlarmMessage;

import java.util.HashMap;
import java.util.List;

public interface AttendanceMapper {
    List<AttendanceAlarmMessage> selectAttAlarmMessages(HashMap<String, Object> hashMap); // 채팅방에 올라올 모든 메세지 조회

    int saveMessage(AttendanceAlarmMessage attendanceAlarmMessage); // 메세지 저장

    int deleteMessage(@Param("msgId") int messageId, @Param("email") String email); // id 와 email 로 메세지 조회 후 맞으면 삭제

}
