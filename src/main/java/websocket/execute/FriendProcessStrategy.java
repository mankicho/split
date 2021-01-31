package websocket.execute;


import component.alarm.AlarmService;
import component.alarm.AlarmServiceImpl;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

@Log4j
public class FriendProcessStrategy implements DataProcessStrategy {

    private AlarmService alarmService = new AlarmServiceImpl();
    private Map<String, WebSocketSession> userMap;

    public void setUserMap(Map<String, WebSocketSession> userMap) {
        this.userMap = userMap;
    }

    @Override
    public void execute(TextMessage tm) {
        JSONObject object = new JSONObject(tm.getPayload());
        String from = object.getString("from");
        String to = object.getString("to");
        // todo 1. 친구요청 메시지 DB에 저장.
        int savedRow = alarmService.saveFriendAddRequest(from, to);

        if (savedRow == 0) { // 저장이 안되었으면(존재하지않는 유저이메일 혹은 서버오류 등등)

        }
        // todo 2. 상대가 접속해있으면 메세지 날림
        WebSocketSession toSession = userMap.get(to);
        if (toSession != null) {
            try {
                toSession.sendMessage(tm);
            } catch (IOException e) {
                log.info(getClass().getName() + " IOException occur!! => " + e.getMessage());
            }
        }
    }

}
