package rank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.ws.client.*;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Component
public class SearchKeywordBroker {

    private WebSocket ws;

    @Value("#{websocket['linux_websocket_server_url']}")
    private String rankServer;

    private String home;

    String myMessage; // 메세지

    // websocket 생성이 실패하면 IOException 발생.
    // handshakes 도중에 error 발생 시 WebsocketException 발생.
    public WebSocket connect() throws IOException, WebSocketException {
        log.info("rankServer = " + rankServer);
        return new WebSocketFactory()
                .setConnectionTimeout(20000)
                .createSocket(rankServer)
                .addListener(new WebSocketAdapter() {

                    // binary message arrived from the server
                    public void onBinaryMessage(WebSocket websocket, byte[] binary) {
                        String str = new String(binary);
                        log.info(str);
                    }

                    // A text message arrived from the server.
                    public void onTextMessage(WebSocket websocket, String message) {
                        log.info(message);

                        myMessage = message;
                    }

                })
                .addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
                .connect();
    }

    public RankServerStatus sendKeywordToSearchRankServer(String str) {

        if (ws == null || ws.getState().compareTo(WebSocketState.CLOSED) == 0) {
            ws = null;
            try {
                ws = connect();
            } catch (IOException | WebSocketException e) {
                e.printStackTrace(); // 후에 제대로된 에러처리 필요.
            }
        }
        if (ws == null) {
            return new RankServerStatus(500);
        }

        JSONObject object = new JSONObject();
        object.put("keyword", str);
        object.put("type", 2);
        if (ws.getState().compareTo(WebSocketState.CONNECTING) == 0 || ws.getState().compareTo(WebSocketState.OPEN) == 0) {
            ws.sendText(object.toString());
        }

        return new RankServerStatus(202);

    }

    public String getKeywordNumFromRankServer() {
        log.info("getKeywordNumFromRankServer");
        JSONObject object = new JSONObject();
        object.put("type", 1);

        if (ws == null || ws.getState().compareTo(WebSocketState.CLOSED) == 0) {
            try {
                ws = connect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ws.getState().compareTo(WebSocketState.OPEN) == 0 || ws.getState().compareTo(WebSocketState.CONNECTING) == 0) {
            ws.sendText(object.toString());
        }

        return myMessage;
    }

    public void sendMessage() {
        JSONObject object = new JSONObject();
        object.put("nickname", "test");
        object.put("type", 3);
        ws.sendText(object.toString());
    }

}
