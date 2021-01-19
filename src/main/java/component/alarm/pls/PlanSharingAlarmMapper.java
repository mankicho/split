package component.alarm.pls;

import org.apache.ibatis.annotations.Param;
import websocket.msg.AlarmMessage;
import websocket.msg.FriendAddRequestAlarmMessage;
import websocket.msg.PlanSharingAlarmMessage;

import java.util.HashMap;
import java.util.List;

public interface PlanSharingAlarmMapper {
    int saveMessage(PlanSharingAlarmMessage planSharingAlarmMessage); // 메세지 저장(누군가 참여했을때)

    int deleteMessage(@Param("msgId") int messageId, @Param("email") String email); // id 와 email 로 메세지 조회 후 맞으면 삭제

    List<PlanSharingAlarmMessage> selectPlanSharingMessages(HashMap<String, Object> hashMap); // 모든 공유플랜 참여 메세지 조회

    int updateCheckFlag(@Param("messageId") int messageId);

    int getMaxSeq(@Param("email") String email);
}
