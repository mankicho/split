package rank;

import com.neovisionaries.ws.client.*;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
public class SearchKeywordBroker {

    private WebSocket ws;
    private String listenMessage;

    // websocket 생성이 실패하면 IOException 발생.
    // handshakes 도중에 error 발생 시 WebsocketException 발생.
    public SearchKeywordBroker() {
        try {
            ws = connect();
        } catch (IOException | WebSocketException e) {
            e.printStackTrace();
        }
    }

    public WebSocket connect() throws IOException, WebSocketException {
        return new WebSocketFactory()
                .setConnectionTimeout(20000)
                .createSocket("http://165.246.197.126:8090/echo/alarm")
                .addListener(new WebSocketAdapter() {

                    // binary message arrived from the server
                    public void onBinaryMessage(com.neovisionaries.ws.client.WebSocket websocket, byte[] binary) {
                        String str = new String(binary);
                        System.out.println(str);
                    }

                    // A text message arrived from the server.
                    public void onTextMessage(com.neovisionaries.ws.client.WebSocket websocket, String message) {
                        listenMessage = message;
                    }

                })
                .addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
                .connect();
    }

    public RankServerStatus sendKeywordToSearchRankServer(String str) {
        if (ws.getState().compareTo(WebSocketState.CLOSED) == 0) {
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
        if (ws.getState().compareTo(WebSocketState.CONNECTING) == 0 || ws.getState().compareTo(WebSocketState.OPEN) == 0) {
            ws.sendText(object.toString());
        }

        return new RankServerStatus(202);

    }


}
