package websocket.execute;

import component.member.MemberMapper;
import component.member.MemberService;
import component.plan.PlanMapper;
import component.plan.PlanService;
import component.plan.PlanServiceImpl;
import component.plan.PlanVO;
import fcm.FcmNotifier;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import websocket.MessageGenerator;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Log4j
@Component
public class PlanSharingInProcessStrategy implements DataProcessStrategy {
    private MemberMapper memberMapper;
    private PlanMapper planMapper;
    private FcmNotifier fcmNotifier;
    private Map<String, WebSocketSession> userMap;

    public void setUserMap(Map<String, WebSocketSession> userMap) {
        this.userMap = userMap;
    }

    public void setMemberMapper(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public void setPlanMapper(PlanMapper planMapper) {
        this.planMapper = planMapper;
    }

    public void setFcmNotifier(FcmNotifier fcmNotifier) {
        this.fcmNotifier = fcmNotifier;
    }

    private void sendMessageToPlanUsers(Map<String, WebSocketSession> userMap, TextMessage tm) {
        JSONObject object = new JSONObject(tm.getPayload());
        int planLogId = object.getInt("planLogId");
        List<String> userEmails = planMapper.getAllEmailOfPlans(planLogId);
        if (userEmails == null) {
            log.info("userEmails");
        } else {
            // 100 명 돌파시
            if (userEmails.size() == 100) {
                PlanVO planVO = planMapper.getPlanVOByPlanLogId(planLogId);
                userEmails.stream().filter(email -> userMap.get(email) != null).forEach(email -> {
                    try {
                        userMap.get(email).sendMessage(tm);
                        if (userEmails.size() == 100) {
                            userMap.get(email).sendMessage(new TextMessage(MessageGenerator.PlanMessage.planMember100MessageWith(planVO)));
                        }
                    } catch (IOException ignored) {
                    }
                });
                List<String> deviceTokens = memberMapper.getDeviceTokens(planLogId);
                deviceTokens.forEach(token -> fcmNotifier.sendFCM(token, "test 제목",
                        MessageGenerator.PlanMessage.planMember100MessageWith(planVO)));
            }

            // 평소 메세지
            userEmails.stream().filter(email -> userMap.get(email) != null).forEach(email -> {
                try {
                    userMap.get(email).sendMessage(tm);
                } catch (IOException ignored) {
                }
            });
        }
    }

    @Override
    public void execute(TextMessage tm) {
        sendMessageToPlanUsers(userMap, tm);
    }

}
