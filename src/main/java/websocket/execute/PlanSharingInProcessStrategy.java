package websocket.execute;

import component.plan.PlanService;
import component.plan.PlanServiceImpl;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Log4j
@Component
public class PlanSharingInProcessStrategy implements DataProcessStrategy {

    private PlanService planService;

    private Map<String, WebSocketSession> userMap;

    public void setUserMap(Map<String, WebSocketSession> userMap) {
        this.userMap = userMap;
    }

    public void setPlanService(PlanService planService) {
        this.planService = planService;
    }
    private void sendMessageToPlanUsers(Map<String, WebSocketSession> userMap, TextMessage tm) {
        JSONObject object = new JSONObject(tm.getPayload());
        int planLogId = object.getInt("planLogId");
        List<String> userEmails = planService.selectsAllEmailOfPlans(planLogId);
        if (userEmails == null) {
            log.info("userEmails");
        } else {
            userEmails.stream().filter(email -> userMap.get(email) != null).forEach(email -> {
                try {
                    userMap.get(email).sendMessage(tm);
                } catch (IOException e) {
                    log.info(getClass().getName() + ": " + e.getMessage());
                }
            });
        }
    }

    @Override
    public void execute(TextMessage tm) {
        sendMessageToPlanUsers(userMap, tm);
    }

}
