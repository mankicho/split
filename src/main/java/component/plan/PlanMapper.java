package component.plan;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface PlanMapper {
    int insertPlan(PlanDTO planDTO);

    int deletePlan(int planLogId);

    int deleteRangePlan(HashMap<String,String> requestBody);

    PlanVO selectByIdAndEmail(HashMap<String, Object> hashMap);

    List<PlanVO> selectsAllPlans(@Param("email") String email);
}
