package websocket.strategy;

import application.ErrorCollector;
import component.alarm.pls.PlanSharingAlarmService;
import component.alarm.pls.PlanSharingAlarmServiceImpl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import websocket.TextMessageGenerator;
import websocket.msg.AlarmMessage;
import websocket.msg.PlanSharingAlarmMessage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PlanSharingOutTransferStrategy extends TransferStrategy {

    private PlanSharingAlarmMessage alarmMessage;
    protected PlanSharingAlarmService planSharingAlarmService = new PlanSharingAlarmServiceImpl(); // 출석체크 플랜 참여 로그 table

    public PlanSharingOutTransferStrategy(PlanSharingAlarmMessage planSharingAlarmMessage) {
        this.alarmMessage = planSharingAlarmMessage;
    }

    /**
     * 출석체크 플랜 퇴실 메세지(중간에 삭제한 경우)
     */
    @Override
    public void transfer() {
        // todo 1. 메세지를 DB 에서 삭제한 후
        planSharingAlarmService.deleteMessage(alarmMessage.getPlanLogId(), alarmMessage.getEmail());
        // todo 2. 플랜 멤버들이 로그인 상태라면 메세지를 보낸다(개별적으로)
        TextMessage message = TextMessageGenerator.generateTextMessagePlanSharingOut((PlanSharingAlarmMessage) alarmMessage);
        WebSocketSession loginUser = userMap.get(alarmMessage.getEmail());
        if (loginUser != null) {
            try {
                loginUser.sendMessage(message);
            } catch (IOException e) {
                ErrorCollector.collect(e.getMessage());
            }
        }
    }
}
