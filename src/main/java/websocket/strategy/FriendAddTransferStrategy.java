package websocket.strategy;

import component.alarm.fri.FriendAddRequestAlarmService;
import component.alarm.fri.FriendAddRequestAlarmServiceImpl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import websocket.TextMessageGenerator;
import websocket.msg.AlarmMessage;
import websocket.msg.FriendAddRequestAlarmMessage;

import java.io.IOException;

public class FriendAddTransferStrategy extends TransferStrategy {

    private FriendAddRequestAlarmMessage alarmMessage;
    private FriendAddRequestAlarmService friendAddRequestAlarmService = new FriendAddRequestAlarmServiceImpl(); // 친구요청 로그 table

    public FriendAddTransferStrategy(FriendAddRequestAlarmMessage message) {
        this.alarmMessage = message;
    }

    /**
     * 친구 요청 메세지
     */
    @Override
    public void transfer() throws IOException {
        // todo 1. 메세지를 DB 에 저장한 후
        friendAddRequestAlarmService.saveMessage(alarmMessage);
        // todo 2. 상대 유저가 로그인 상태라면 메세지를 보낸다
        String toEmail = alarmMessage.getToEmail();
        WebSocketSession user = userMap.get(toEmail);
        if (user != null) {
            alarmMessage.setCode(200);
            TextMessage message = TextMessageGenerator.generateTextMessageFriendAddRequest(alarmMessage);
            user.sendMessage(message);
        }
    }
}
