package component.plan;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface PlanMapper {
    int insertPlan(PlanDTO planDTO); // 플랜 예약

    int deletePlan(int planLogId); // 플랜 삭제

    int deleteRangePlan(HashMap<String, String> requestBody); // 플랜 한번에 삭제

    PlanVO selectByIdAndEmail(HashMap<String, Object> hashMap); // ID 랑 이메일로 플랜 조회

    List<PlanVO> selectsAllPlans(@Param("email") String email); // 내 플랜 다 가져오기

    int changePublic(NonPubPlanDTO nonPubPlanDTO); // 공개/비공개 전환

    int addAttendanceLog(PlanAttendanceDTO planAttendanceDTO); // 출석체크 기록 (누구누구가 출석했습니다.) 넣기

    List<PlanAttendanceDTO> getPlanAttendances(@Param("planLogId") int planLogId); // 출석체크기록 가져오기

}
