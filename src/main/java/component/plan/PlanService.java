package component.plan;

import org.apache.ibatis.annotations.Param;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface PlanService {
    int insertPlan(PlanDTO planDTO) throws ParseException;

    int testInsertPlan(PlanDTO planDTO) throws ParseException;

    int insertRangePlan(List<PlanDTO> planDTOS);

    int deletePlan(int planLogId);

    int deleteRangePlan(HashMap<String, String> requestBody);

    PlanVO selectByIdAndEmail(HashMap<String, Object> hashMap);

    List<PlanVO> selectsAllPlans(String email);

    List<String> selectsAllEmailOfPlans(int planLogId);

    PlanVO selectTodayPlan(HashMap<String, Object> hashMap);

    List<PlanVO> selectsAllPlansAtCertainZone( String placeSetting);

    List<PlanVO> getPlansBySearching(String key);

}
