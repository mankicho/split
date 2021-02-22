package websocket.execute.note;

import com.google.gson.JsonObject;
import component.note.NoteDTO;
import component.note.NoteMapper;
import component.note.NoteVO;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import websocket.MessageGenerator;
import websocket.execute.DataProcessStrategy;

import java.io.IOException;
import java.util.Map;


@Log4j2
public class NoteProcessStrategy extends DataProcessStrategy {

    private Map<String, WebSocketSession> userMaps;
    private NoteMapper noteMapper;

    public NoteProcessStrategy(Map<String, WebSocketSession> userMaps, NoteMapper noteMapper) {
        this.userMaps = userMaps;
        this.noteMapper = noteMapper;
    }

    @Override
    public void execute(TextMessage tm) {
        JSONObject object = new JSONObject(tm.getPayload());
        log.info(tm.getPayload());
        String toEmail = object.getString("toEmail");
        String fromEmail = object.getString("fromEmail");

        WebSocketSession to = userMaps.get(toEmail);
        WebSocketSession from = userMaps.get(fromEmail);
        try {
            int insertedRow = noteMapper.saveNote(textMessageToNoteDTO(object));

            if (insertedRow != 0) { // db 에 정상적으로 쪽지를 저장하면
                sendMessage(to, tm); // 메시지 전송
            } else { // db 에 정상적으로 쪽지가 저장이 안되면
                sendMessageIfFail(from);
            }
        } catch (IOException e) {
            e.printStackTrace();
            sendMessageIfFail(from);
            log.info("note process error => " + e.getMessage());
        }
    }

    private void sendMessage(WebSocketSession socketSession, TextMessage tm) throws IOException {
        if (socketSession != null) {
            socketSession.sendMessage(tm);
        }
    }

    private NoteDTO textMessageToNoteDTO(JSONObject jsonObject) {
        String uuid = jsonObject.getString("uuid");
        String fromEmail = jsonObject.getString("fromEmail");
        String toEmail = jsonObject.getString("toEmail");
        String content = jsonObject.getString("content");

        return new NoteDTO(uuid, fromEmail, toEmail, content);
    }
}
