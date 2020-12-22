package component.plan.off;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface OfficialPlanService {
    List<OfficialPlanDTO> selects();

    int insertOfficialPlan(OfficialPlanDTO officialPlanDTO);

    int deleteOfficialPlan(HashMap<String, Object> hashMap);


}
