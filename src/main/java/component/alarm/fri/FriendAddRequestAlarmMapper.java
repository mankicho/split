package component.alarm.fri;

import org.apache.ibatis.annotations.Param;
import websocket.msg.AlarmMessage;
import websocket.msg.AttendanceAlarmMessage;
import websocket.msg.FriendAddRequestAlarmMessage;

import java.util.HashMap;
import java.util.List;

public interface FriendAddRequestAlarmMapper {
    int saveMessage(FriendAddRequestAlarmMessage friendAddRequestAlarmMessage); // 메세지 저장

    List<FriendAddRequestAlarmMessage> selectAttAlarmMessages(HashMap<String, Object> hashMap); // 모든 친구추가 메세지 조회

    int deleteMessage(@Param("msgId") int messageId, @Param("email") String email); // id 와 email 로 메세지 조회 후 맞으면 삭제

    int updateCheckFlag(@Param("messageId") int messageId);

    int getMaxSeq(@Param("email") String email);
}
