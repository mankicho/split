package component.plan.off;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface OfficialPlanService {
    List<OfficialPlanVO> selects();

    int insertOfficialPlan(OfficialPlanDTO officialPlanDTO);

    int deleteOfficialPlan(HashMap<String, Object> hashMap);

    int selectByIdAndEmail(HashMap<String, Object> hashMap);

    List<OfficialPlanLogVO> selectsAllPlans(String email,String sDate);

}
