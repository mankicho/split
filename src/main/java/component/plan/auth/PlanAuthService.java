package component.plan.auth;

import component.plan.PlanAttendanceDTO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface PlanAuthService {
    int planAuthLog(HashMap<String, Object> hashMap);

    List<PlanAttendanceDTO> getPlanAttendances(int planLogId); // 출석체크기록 가져오기

    TodayPlanAuthVO getAuthNumberOfMyPlan(int planLogId);

}
