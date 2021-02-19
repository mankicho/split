package websocket.execute;

import component.plan.PlanService;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ChattingProcessStrategy implements DataProcessStrategy {
    private Map<String, WebSocketSession> userMap;
    private PlanService planService;

    public void setPlanService(PlanService planService) {
        this.planService = planService;
    }

    public void setUserMap(Map<String, WebSocketSession> userMap) {
        this.userMap = userMap;
    }

    @Override
    public void execute(TextMessage tm) {
        // todo 1. message 에서 plan 의 고유 id 식별.
        JSONObject object = new JSONObject(tm.getPayload());
        // todo 2. 전송한 message 를 db 에 저장.

        // todo 3. plan 내의 모든 member 들에게 message 전송.
        List<String> members = planService.getAllEmailOfPlans(object.getInt("planLogId"));
        if (members != null && !members.isEmpty()) {
            members.stream().filter(member -> userMap.get(member) != null).forEach(member -> {
                try {
                    userMap.get(member).sendMessage(tm);
                } catch (IOException ignored) {
                }
            });
        }
    }
}
