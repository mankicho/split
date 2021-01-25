package websocket;

import component.member.MemberMapper;
import component.plan.auth.PlanAuthService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import websocket.execute.*;

import java.io.IOException;
import java.util.*;

@Log4j
public class EchoHandler extends TextWebSocketHandler {

    @Setter(onMethod_ = {@Autowired})
    private MemberMapper memberMapper;


    //로그인 한 전체
    private List<WebSocketSession> sessions = new ArrayList<>();

    // 1대1
    private Map<String, WebSocketSession> userSessionsMap = new HashMap<>();
    private Map<String, WebSocketSession> cafeSessionMap = new HashMap<>();

    /**
     * @param session this function called when client' session is connected
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(session.getId() + " is connect");
        List<String> cafe = session.getHandshakeHeaders().get("cafe");
        System.out.println("cafe header = " + cafe);
        sessions.add(session);
        String senderEmail = getEmail(session);
        userSessionsMap.put(senderEmail, session);
        if (cafe != null) {
            String cafeCode = cafe.get(0);
            System.out.println("cafe = " + cafeCode);
            if (cafeSessionMap.get(cafeCode) != null) {
                cafeSessionMap.remove(cafeCode);
            }
            cafeSessionMap.put(cafeCode, session);
            System.out.println(cafeSessionMap);
        }
        session.sendMessage(new TextMessage("1"));
    }

    /**
     * @param session this function called when client' session is disconnected
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        userSessionsMap.remove(session.getId());
        System.out.println(session.getId() + "is disconnected");
        sessions.remove(session);
        cafeSessionMap.remove(session.getId());
    }

    /**
     * @param session
     * @param message this function handles user's message
     *                function for sending real-time notification to clients
     *                ex 1. if client A brings plans of client B, then server send real-time message to client B
     *                message form (JSON)
     *                {
     *                "toEmail" : {toEmail},
     *                "fromEmail" : {fromEmail},
     *                "title" : {message_title},
     *                "message" : {message},
     *                "messageSeq" : {message_sequence},
     *                "checkFlag" : {check_flag},
     *                "transferTime" : {transfer_time}
     *                }
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("handleTextMessage = " + message.getPayload());
        // todo 1. 적절한 요청인가? (email 이 실제로 DB 에 저장되어있는가)
        // todo 2. 메세지 요청 유형 파악
//        AlarmMessage alarmMessage = alarmMessageParser.parse(message);
//        // todo 3. 유형에 맞는 메소드 수행
//        AlarmMessageServiceExecutor serviceExecutor = new AlarmMessageServiceExecutor(alarmMessage, session, sessions, userSessionsMap);
//        serviceExecutor.execute();
        DataProcessStrategy dps = parseTextMessage(message);
        dataProcess(dps);
        sendMessageToCafe(message);
    }

    /**
     * @param session client -> server (contains header("user",{user_email})
     *                server analyzes the headers and extracts user email
     * @return user email
     */
    private String getEmail(WebSocketSession session) {
        List<String> userList = session.getHandshakeHeaders().get("user");
        if (userList == null) {
            return session.getId();
        }
        String user = userList.get(0);
        return user == null ? session.getId() : user;
    }

    private DataProcessStrategy parseTextMessage(TextMessage message) {
        JSONObject object = new JSONObject(message.getPayload());
        switch (object.getInt("type")) {
            case 1:
                return new FriendProcessStrategy();
            case 2:
                return new AttendanceProcessStrategy();
            case 3:
                return new PlanSharingInProcessStrategy();
            case 4:
                return new PlanSharingOutProcessStrategy();
            default:
                return null;
        }
    }

    private void dataProcess(DataProcessStrategy dps) {
        if (dps != null) {
            dps.execute();
        }
    }

    private void sendMessageToCafe(TextMessage tm) throws IOException {
        JSONObject object = new JSONObject(tm.getPayload());
        System.out.println("object = " + object);
        WebSocketSession cafe = cafeSessionMap.get(object.getString("cafe"));
        System.out.println("message(Cafe) = " + cafe);
        if (cafe != null) {
            cafe.sendMessage(new TextMessage(object.getString("user")));
            System.out.println("send");
        }
    }
}
