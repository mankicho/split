package websocket;

import component.plan.PlanVO;
import org.json.JSONObject;

public class MessageGenerator {

    public static class PlanMessage {
        public static String planMember100MessageWith(PlanVO planVO) {
            return "회원님의 '" + planVO.getPlanName() + "' 플랜의 회원이 100명을 돌파했습니다";
        }

    }


    public static class NoteMessage{
        public static String failSaveNoteMessage(){
            return "서버에 오류가 생겼습니다. 잠시후에 다시 시도해주세요";
        }
    }

}
