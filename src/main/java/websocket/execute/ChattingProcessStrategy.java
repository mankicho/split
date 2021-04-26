//package websocket.execute;
//
//import component.chat.ChattingMapper;
//import component.chat.dto.ChattingMessageDTO;
//import lombok.RequiredArgsConstructor;
//import org.json.JSONObject;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//@Component
//@RequiredArgsConstructor
//public class ChattingProcessStrategy extends DataProcessStrategy {
//    private Map<String, WebSocketSession> userMap;
//
//    public void setUserMap(Map<String, WebSocketSession> userMap) {
//        this.userMap = userMap;
//    }
//
//    private final ChattingMapper chattingMapper;
//
//    @Override
//    public void execute(TextMessage tm) {
//        // todo 1. message 에서 채팅방의 의 고유 uuid 식별.
//        JSONObject object = new JSONObject(tm.getPayload());
//        int schoolId = object.getInt("schoolId");
//        int classId = object.getInt("classId");
//        // todo 2. 전송한 message 를 db 에 저장.
//
//        ChattingMessageDTO messageDTO = ChattingMessageDTO.builder()
//                .message(object.getString("message"))
//                .mType(object.getInt("mType"))
//                .memberEmail(object.getString("memberEmail"))
//                .schoolId(object.getInt("schoolId"))
//                .classId(object.getInt("classId"))
//                .build();
//
//        ChattingMessageDTO chattingMessageDTO = chattingMapper.send(messageDTO);
//
//        // todo 3. plan 내의 모든 member 들에게 message 전송.
//    }
//}
