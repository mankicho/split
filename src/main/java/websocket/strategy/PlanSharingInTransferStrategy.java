package websocket.strategy;

import component.alarm.pls.PlanSharingAlarmService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import websocket.TextMessageGenerator;
import websocket.msg.AlarmMessage;
import websocket.msg.PlanSharingAlarmMessage;

public class PlanSharingInTransferStrategy extends TransferStrategy {

    private PlanSharingAlarmMessage alarmMessage;
    @Setter(onMethod_ = {@Autowired})
    protected PlanSharingAlarmService planSharingAlarmService; // 출석체크 플랜 참여 로그 table

    public PlanSharingInTransferStrategy(PlanSharingAlarmMessage planSharingAlarmMessage) {
        this.alarmMessage = planSharingAlarmMessage;
    }

    @Override
    public void transfer() {
        // todo 1. 메세지를 DB 에 저장한 후

        // todo 2. 플랜 멤버들이 로그인 상태라면 메세지를 보낸다(개별적으로)
        TextMessage message = TextMessageGenerator.generateTextMessagePlanSharingOut((PlanSharingAlarmMessage) alarmMessage);
    }
}
