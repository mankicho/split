package websocket.strategy;

import component.alarm.att.AttendanceService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import websocket.TextMessageGenerator;
import websocket.msg.AlarmMessage;
import websocket.msg.AttendanceAlarmMessage;

public class AttendanceTransferStrategy extends TransferStrategy {

    private AttendanceAlarmMessage alarmMessage;
    @Setter(onMethod_ = {@Autowired})
    protected AttendanceService attendanceService; // 출석체크 로그 table

    public AttendanceTransferStrategy(AttendanceAlarmMessage attendanceAlarmMessage) {
        this.alarmMessage = attendanceAlarmMessage;
    }

    /**
     * 출석체크 수행 메세지
     */
    @Override
    public void transfer() {
        // todo 1. 메세지를 DB 에 저장한 후

        // todo 2. 플랜 멤버들이 로그인 상태라면 메세지를 보낸다(개별적으로)
        TextMessage message = TextMessageGenerator.generateTextMessageAttendance((AttendanceAlarmMessage) alarmMessage);
    }
}
