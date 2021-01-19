package websocket;

import component.member.MemberService;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import websocket.msg.AttendanceAlarmMessage;
import websocket.msg.FriendAddRequestAlarmMessage;
import websocket.msg.PlanSharingAlarmMessage;

public class TextMessageGenerator {
    /**
     * @param friendAddRequestAlarmMessage 친구요청에 대한 응답메세지(OOO 님이 친구신청을 했습니다.)
     * @return
     */
    public static TextMessage generateTextMessageFriendAddRequest(FriendAddRequestAlarmMessage friendAddRequestAlarmMessage) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fromNickname", friendAddRequestAlarmMessage.getFromNickname());
        jsonObject.put("fromEmail", friendAddRequestAlarmMessage.getFromEmail());
        jsonObject.put("toEmail", friendAddRequestAlarmMessage.getToEmail());
        jsonObject.put("datetime", friendAddRequestAlarmMessage.getDateTime());
        jsonObject.put("type", friendAddRequestAlarmMessage.getType());
        jsonObject.put("code", friendAddRequestAlarmMessage.getCode());
        return new TextMessage(jsonObject.toString());
    }

    /**
     * @param attendanceAlarmMessage 출석체크에 대한 메세지(OOO 님이 출석체크를 했습니다.)
     * @return
     */
    public static TextMessage generateTextMessageAttendance(AttendanceAlarmMessage attendanceAlarmMessage) {
        StringBuilder sb = new StringBuilder();
        return new TextMessage(sb.toString());
    }

    public static TextMessage generateTextMessagePlanSharingIn(PlanSharingAlarmMessage planSharingAlarmMessage) {
        StringBuilder sb = new StringBuilder();
        return new TextMessage(sb.toString());
    }

    public static TextMessage generateTextMessagePlanSharingOut(PlanSharingAlarmMessage planSharingAlarmMessage) {
        StringBuilder sb = new StringBuilder();
        return new TextMessage(sb.toString());
    }
}
