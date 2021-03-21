package websocket;

import component.member.vo.MemberDeviceVO;
import component.member.MemberMapper;
import component.note.NoteMapper;
import component.school.SchoolMapper;
import component.zone.ZoneMapper;
import fcm.FcmNotifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import websocket.execute.*;
import websocket.execute.note.NoteProcessStrategy;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

/**
 * 현재 집중시간 모드 기능을 이용하고 있는 총 회원 파악용도
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class EchoHandler extends TextWebSocketHandler {

    private final MemberMapper memberMapper;
    private final ZoneMapper zoneMapper;
    private final SchoolMapper schoolMapper;
    private final FcmNotifier fcmNotifier;
    private final NoteMapper noteMapper;

    //로그인 한 전체
    private List<WebSocketSession> sessions = new ArrayList<>();  // 실시간 어플을 이용하고 있는 유저
    private List<String> timers = new ArrayList<>(); // 실시간 집중시간 이용 유저

    // 1대1
    private Map<String, WebSocketSession> userEmailSocketSessionMap = new HashMap<>(); // 유저 이메일 : 세션
    private Map<String, String> userSessionNicknameMap = new HashMap<>(); // 유저 세션 ID : 유저 이메일
    private Map<String, WebSocketSession> cafeCodeSocketSessionMap = new HashMap<>(); // 카페 : 세션
    private Map<String, String> cafeCodeSessionIdMap = new HashMap<>(); // 카페명 : 세션 ID

    // Date Format
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
        userEmailSocketSessionMap.remove(userSessionNicknameMap.get(session.getId())); // 유저 제거
        userSessionNicknameMap.remove(session.getId()); // 유저 제거
        sessions.remove(session); // 유저 제거
        cafeCodeSocketSessionMap.remove(cafeCodeSessionIdMap.get(session.getId())); // 카페 제거
        cafeCodeSessionIdMap.remove(session.getId()); // 카페 제거
        timers.removeIf(str -> str.equals(session.getId()));
    }

    /**
     * @param webSocketSession
     * @param message          this function handles user's message
     *                         function for sending real-time notification to clients
     *                         ex 1. if client A brings plans of client B, then server send real-time message to client B
     *                         message form (JSON)
     *                         {
     *                         "toEmail" : {toEmail},
     *                         "fromEmail" : {fromEmail},
     *                         "title" : {message_title},
     *                         "message" : {message},
     *                         "messageSeq" : {message_sequence},
     *                         "checkFlag" : {check_flag},
     *                         "transferTime" : {transfer_time}
     *                         }
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) throws Exception {

        // todo 1. 클라이언트가 날린 메세지 해석
        DataProcessStrategy dps = parseTextMessage(webSocketSession, message);
        // todo 2. 메세지에 맞는 함수 호출(DB에 데이터 저장 or 적절한 유저에게 메세지 전송 등)
        dataProcess(dps, message);
    }


    // 유저가 보낸 메세지 처리
    private DataProcessStrategy parseTextMessage(WebSocketSession webSocketSession, TextMessage message) {
        JSONObject object = new JSONObject(message.getPayload());
        switch (object.getInt("type")) {
            case 1: // 친구요청
                FriendProcessStrategy dps = new FriendProcessStrategy();
                dps.setUserMap(userEmailSocketSessionMap);
                return dps;
            case 2: // 출석체크
                AttendanceProcessStrategy aps = new AttendanceProcessStrategy();
                aps.setCafeMap(cafeCodeSocketSessionMap);
                aps.setUserMap(userEmailSocketSessionMap);
                return aps;
            case 5: // 집중시간
                String user = object.getString("user");
                int mode = object.getInt("mode");
                if (mode == 1 && !timers.contains(user)) { // 집중 모드면
                    timers.add(webSocketSession.getId());
                } else {
                    timers.removeIf(str -> str.equals(webSocketSession.getId()));
                }

                ConcentrateModeOnStrategy tps = new ConcentrateModeOnStrategy();
                tps.setTimerSessions(timers);
                tps.setLoginUsers(sessions);
                return tps;
            case 6:
                // 팔로우 요청
                FollowProcessStrategy fps = new FollowProcessStrategy();
                fps.setUserMap(userEmailSocketSessionMap);
                return fps;
            case 7:
                // 쪽지 보내기
                return new NoteProcessStrategy(userEmailSocketSessionMap, noteMapper);
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
        if ((cafeHeaders = httpHeaders.get("cafe")) != null) { // 카페 코드 
            log.info(cafeHeaders.get(0) + " is connected, session:" + socketSession.getId());
            int existedRow = zoneMapper.isExist(cafeHeaders.get(0));
            if (existedRow == 1) {
                cafeCodeSocketSessionMap.put(cafeHeaders.get(0), socketSession);
                cafeCodeSessionIdMap.put(socketSession.getId(), cafeHeaders.get(0));
            } else {
                log.info("not exists data in DB : \"" + cafeHeaders.get(0) + "\"");
            }
        }

        if ((userHeaders = httpHeaders.get("user")) != null) { // 이메일
            log.info(userHeaders.get(0) + " is connected, session:" + socketSession.getId());
            userEmailSocketSessionMap.put(userHeaders.get(0), socketSession); // 이메일 : 소켓세션
            userSessionNicknameMap.put(socketSession.getId(), userHeaders.get(0)); // 세션 ID : 이메일
        }
    }

    // ------------------------------------------------- 시스템 알림 메세지 -------------------------------------------------

    @Scheduled(cron = "0 30 * * * *") // 출석체크 시스템 알림
    public void sendSystemMessageForAttendance() {
        log.info("current thread is " + Thread.currentThread().getName());
        DayOfWeek weekday = LocalDate.now().getDayOfWeek(); // 요일
        List<MemberDeviceVO> deviceList = schoolMapper.getDevicesForPushNotificationOfAttendance(getSquareOfWeekday(weekday)); // 출석체크가 임박한 유저의 디바이스 토큰 가져오기

        log.info("deviceList = " + deviceList);
        if (deviceList != null && !deviceList.isEmpty()) { // 디바이스 토큰 값ㅇ ㅣ존재하면
            deviceList.forEach(vo -> { // for 문 돌면서 push alarm 전송
                fcmNotifier.sendFCM(vo.getDeviceToken(), "시스템 알림",
                        memberDeviceTokenToString(vo));
                try {
                    WebSocketSession user = userEmailSocketSessionMap.get(vo.getMemberEmail()); // 웹소켓으로 실시간 알림도 전송
                    // todo 1. 알림 보낸 내역에 저장.
                    if (user != null) {
                        user.sendMessage(new TextMessage("출석체크 30분 전입니다."));
                    }
                } catch (IOException ignored) {
                    log.info("text send error => user disconnected");
                }
            });
        }
    }

//    @Scheduled(cron ="") // 플랜 시스템 알림
//    public void sendSystemMessageForPlan(){
//
//    }
//
//    @Scheduled(cron ="") // 팔로잉 시스템 알림
//    public void sendSystemMessageForFollowing(){
//
//    }
//
//    @Scheduled(cron ="") //  시스템 알림
//    public void sendSystemMessageForNote(){
//
//    }

    // ------------------------------------------------- util 함수 -------------------------------------------------
    private int getSquareOfWeekday(DayOfWeek week) {
        switch (week) {
            case MONDAY:
                return 1;
            case TUESDAY:
                return 2;
            case WEDNESDAY:
                return 4;
            case THURSDAY:
                return 8;
            case FRIDAY:
                return 16;
            case SATURDAY:
                return 32;
            default:
                return 64;
        }
    }

    private String memberDeviceTokenToString(MemberDeviceVO memberDeviceVO) {
        return "회원님의 '" + memberDeviceVO.getSchoolName() + "' 출석 인증까지 30분 남았습니다!";
    }
}
