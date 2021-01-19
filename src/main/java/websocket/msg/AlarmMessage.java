package websocket.msg;

import lombok.Data;

@Data
public abstract class AlarmMessage {
    protected int type;
    protected int code;
    protected String dateTime;
}
