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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
public class EchoHandler extends TextWebSocketHandler {

    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;
    //로그인 한 전체
    List<WebSocketSession> sessions = new ArrayList<>();
    // 1대1
    Map<String, WebSocketSession> userSessionsMap = new HashMap<>();

    /**
     * @param session this function called when client' session is connected
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(session.getId() + " is connected");
        sessions.add(session);
        String senderEmail = getEmail(session);
        userSessionsMap.put(senderEmail, session);
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
        String msg = message.getPayload(); // 메세지 가져와서
        JSONObject jsonMessage = new JSONObject(msg); // JSON 형성
        String userEmail = jsonMessage.getString("toEmail"); // JSON 에서 email 추출
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        String searchedUserEmail = memberMapper.isExistEmail(userEmail);
        if (searchedUserEmail == null) {
            TextMessage errorMessage = new TextMessage("존재하지 않는 이메일입니다.");
            session.sendMessage(errorMessage);
            return;
        }
        WebSocketSession loginClient = userSessionsMap.get(userEmail); // 유저 세션 map 에서 email 기반 세션 추출
        if (loginClient != null) { // 로그인한 유저가 있으면
            loginClient.sendMessage(message); // 메세지를 보낸다
        }
        AlarmMessageMapper messageMapper = sqlSession.getMapper(AlarmMessageMapper.class);
        AlarmMessageDTO alarmMessageDTO = setAlarmMessageDTO(message, messageMapper);

        try {
            int status = messageMapper.saveMessage(alarmMessageDTO); // 메세지 전송 유무에 상관없이 메세지 저장.
        } catch (MysqlDataTruncation trunc) {
            System.out.println("mysql Data Truncation 에러");
        }
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

    private AlarmMessageDTO setAlarmMessageDTO(TextMessage message, AlarmMessageMapper messageMapper) {
        JSONObject jsonMessage = new JSONObject(message.getPayload());
        String userEmail = jsonMessage.getString("toEmail");

        int maxSeq;
        try {
            maxSeq = messageMapper.getMaxSeq(userEmail) + 1;
        } catch (BindingException e) { // 알림이 하나도 없어서 getMaxSeq 가 null 을 리턴하는 경우
            maxSeq = 0;
        }

        AlarmMessageDTO messageDTO = new AlarmMessageDTO(jsonMessage.getString("toEmail"), jsonMessage.getString("fromEmail")
                , jsonMessage.getString("title"), jsonMessage.getString("message"));

        messageDTO.setMessageSeq(maxSeq);
        return messageDTO;
    }

}
