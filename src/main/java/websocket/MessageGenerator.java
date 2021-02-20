package websocket;

import component.plan.PlanVO;

public class MessageGenerator {
    public static String planMember100MessageWith(PlanVO planVO){
        return "회원님의 '"+planVO.getPlanName()+"' 플랜의 회원이 100명을 돌파했습니다";
    }

}
