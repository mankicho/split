package websocket;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import component.alarm.AlarmMessageDTO;
import component.alarm.AlarmMessageMapper;
import component.member.MemberMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import websocket.msg.AlarmMessage;

import java.util.*;

@Log4j
public class EchoHandler extends TextWebSocketHandler {

    @Setter(onMethod_ = {@Autowired})
    private MemberMapper memberMapper;

    @Setter(onMethod_ = {@Autowired})
    private AlarmMessageMapper alarmMessageMapper;


    private AlarmMessageParser alarmMessageParser = new AlarmMessageParser();
    //로그인 한 전체
    private List<WebSocketSession> sessions = new ArrayList<>();

    // 1대1
    private Map<String, WebSocketSession> userSessionsMap = new HashMap<>();
    private Map<String, WebSocketSession> sessionIdMap = new HashMap<>();

    /**
     * @param session this function called when client' session is connected
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(session.getId() + " is connect");
        sessions.add(session);
        String senderEmail = getEmail(session);
        userSessionsMap.put(senderEmail, session);
        sessionIdMap.put(session.getId(), session);
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
        // todo 1. 적절한 요청인가? (email 이 실제로 DB 에 저장되어있는가)
        // todo 2. 메세지 요청 유형 파악
//        AlarmMessage alarmMessage = alarmMessageParser.parse(message);
//        // todo 3. 유형에 맞는 메소드 수행
//        AlarmMessageServiceExecutor serviceExecutor = new AlarmMessageServiceExecutor(alarmMessage, session, sessions, userSessionsMap);
//        serviceExecutor.execute();
        TextMessage msg = new TextMessage(session.getId());
        session.sendMessage(msg);
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

    private AlarmMessageDTO setAlarmMessageDTO(TextMessage message) {
        JSONObject jsonMessage = new JSONObject(message.getPayload());
        String userEmail = jsonMessage.getString("toEmail");

        int maxSeq;
        try {
            maxSeq = alarmMessageMapper.getMaxSeq(userEmail) + 1;
        } catch (BindingException e) { // 알림이 하나도 없어서 getMaxSeq 가 null 을 리턴하는 경우
            maxSeq = 0;
        }

        AlarmMessageDTO messageDTO = new AlarmMessageDTO(jsonMessage.getString("toEmail"), jsonMessage.getString("fromEmail")
                , jsonMessage.getString("title"), jsonMessage.getString("message"));

        messageDTO.setMessageSeq(maxSeq);
        return messageDTO;
    }

}
