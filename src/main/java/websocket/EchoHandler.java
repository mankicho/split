package websocket;

import component.member.MemberMapper;
import component.plan.auth.PlanAuthService;
import component.zone.ZoneMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @Setter(onMethod_ = {@Autowired})
    private ZoneMapper zoneMapper;

    //로그인 한 전체
    private List<WebSocketSession> sessions = new ArrayList<>();
    private List<String> timers = new ArrayList<>();

    // 1대1
    private Map<String, WebSocketSession> userSessionsMap = new HashMap<>();
    private Map<String, String> userSessionIdMap = new HashMap<>();
    private Map<String, WebSocketSession> cafeSessionMap = new HashMap<>();
    private Map<String, String> cafeSessionIdMap = new HashMap<>();

    /**
     * @param session this function called when client' session is connected
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("ack")); // 연결이 수행되면
        sessions.add(session);
        classifyUser(session);
    }

    /**
     * @param session this function called when client' session is disconnected
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session.getId() + " is disconnected");
        log.info(session.getHandshakeHeaders());
        userSessionsMap.remove(userSessionIdMap.get(session.getId()));
        userSessionIdMap.remove(session.getId());
        sessions.remove(session);
        cafeSessionMap.remove(cafeSessionIdMap.get(session.getId()));
        cafeSessionIdMap.remove(session.getId());
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
        log.info("handleText");
        // todo 1. 클라이언트가 날린 메세지 해석
        DataProcessStrategy dps = parseTextMessage(message);
        // todo 2. 메세지에 맞는 함수 호출(DB에 데이터 저장 or 적절한 유저에게 메세지 전송 등)
        dataProcess(dps, message);
    }

    private DataProcessStrategy parseTextMessage(TextMessage message) {
        JSONObject object = new JSONObject(message.getPayload());
        switch (object.getInt("type")) {
            case 1:
                FriendProcessStrategy dps = new FriendProcessStrategy();
                dps.setUserMap(userSessionsMap);
                return dps;
            case 2:
                AttendanceProcessStrategy aps = new AttendanceProcessStrategy();
                aps.setCafeMap(cafeSessionMap);
                return aps;
//            case 3:
//                return new PlanSharingInProcessStrategy();
//            case 4:
//                return new PlanSharingOutProcessStrategy();
            case 5:
                String user = object.getString("user");
                if (!timers.contains(user)) {
                    timers.add(user);
                }
                TimerProcessStrategy tps = new TimerProcessStrategy();
                tps.setTimerSessions(timers);
                tps.setLoginUsers(sessions);
                return tps;
            default:
                return null;
        }
    }

    private void dataProcess(DataProcessStrategy dps, TextMessage tm) {
        if (dps != null) {
            dps.execute(tm);
        }
    }

    private void classifyUser(WebSocketSession socketSession) {
        HttpHeaders httpHeaders = socketSession.getHandshakeHeaders();
        List<String> cafeHeaders;
        List<String> userHeaders;
        if ((cafeHeaders = httpHeaders.get("cafe")) != null) {
            log.info(cafeHeaders.get(0) + " is connected, session:" + socketSession.getId());
            int existedRow = zoneMapper.isExist(cafeHeaders.get(0));
            if (existedRow == 1) {
                cafeSessionMap.put(cafeHeaders.get(0), socketSession);
                cafeSessionIdMap.put(socketSession.getId(), cafeHeaders.get(0));
            } else {
                log.info("not exists data in DB : \"" + cafeHeaders.get(0) + "\"");
            }
        }

        if ((userHeaders = httpHeaders.get("user")) != null) {
            log.info(userHeaders.get(0) + " is connected, session:" + socketSession.getId());
            userSessionsMap.put(userHeaders.get(0), socketSession);
            userSessionIdMap.put(socketSession.getId(), userHeaders.get(0));
        }

    }
}
