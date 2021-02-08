package websocket;

import component.member.MemberMapper;
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

import java.util.*;

/**
 * 현재 집중시간 모드 기능을 이용하고 있는 총 회원 파악용도
 */
@Log4j
public class EchoHandler extends TextWebSocketHandler {

    @Setter(onMethod_ = {@Autowired})
    private MemberMapper memberMapper;

    @Setter(onMethod_ = {@Autowired})
    private ZoneMapper zoneMapper;

    //로그인 한 전체
    private List<WebSocketSession> sessions = new ArrayList<>();  // 실시간 어플을 이용하고 있는 유저
    private List<String> timers = new ArrayList<>(); // 실시간 집중시간 이용 유저

    // 1대1
    private Map<String, WebSocketSession> userSessionsMap = new HashMap<>(); // 유저 : 세션
    private Map<String, String> userSessionIdMap = new HashMap<>(); // 유저 이메일 : 유저 세션 ID
    private Map<String, WebSocketSession> cafeSessionMap = new HashMap<>(); // 카페 : 세션
    private Map<String, String> cafeSessionIdMap = new HashMap<>(); // 카페명 : 세션 ID

    /**
     * @param session this function called when client' session is connected
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info(session.getId() + " is connected");
        session.sendMessage(new TextMessage("ack")); // 연결이 수행되면
        sessions.add(session); // 이용유저 추가
        classifyUser(session); // 유저 분류
    }

    /**
     * @param session this function called when client' session is disconnected
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session.getId() + " is disconnected");
        userSessionsMap.remove(userSessionIdMap.get(session.getId())); // 유저 제거
        userSessionIdMap.remove(session.getId()); // 유저 제거
        sessions.remove(session);
        cafeSessionMap.remove(cafeSessionIdMap.get(session.getId())); // 카페 제거
        cafeSessionIdMap.remove(session.getId()); // 카페 제거
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
            case 1: // 친구요청
                FriendProcessStrategy dps = new FriendProcessStrategy();
                dps.setUserMap(userSessionsMap);
                return dps;
            case 2: // 출석체크
                AttendanceProcessStrategy aps = new AttendanceProcessStrategy();
                aps.setCafeMap(cafeSessionMap);
                return aps;
//            case 3: // 플랜 참여
//                return new PlanSharingInProcessStrategy();
//            case 4: // 플랜 참여 철회
//                return new PlanSharingOutProcessStrategy();
            case 5: // 집중시간
                String user = object.getString("user");
                int mode = object.getInt("mode");
                if (mode == 1 && !timers.contains(user)) { // 집중 모드면
                    timers.add(user);
                } else {
                    timers.removeIf(str -> str.equals(user));
                }
                ConcentrateModeOnStrategy tps = new ConcentrateModeOnStrategy();
                tps.setTimerSessions(timers);
                tps.setLoginUsers(sessions);
                return tps;
            case 6:
                // 팔로우 요청
                FollowProcessStrategy fps = new FollowProcessStrategy();
                fps.setUserMap(userSessionsMap);
                return fps;
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
