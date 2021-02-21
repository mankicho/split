package exception.error;

import lombok.Data;

public class JsonParseMessage {
    private String msg;

    public JsonParseMessage(String msg) {
        this.msg = msg;
    }
}
