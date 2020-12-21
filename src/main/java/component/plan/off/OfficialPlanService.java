package component.plan.off;

import java.util.HashMap;
import java.util.List;

public interface OfficialPlanService {
    List<OfficialPlanDTO> selects();

    int insertOfficialPlan(OfficialPlanDTO officialPlanDTO);

    int deleteOfficialPlan(HashMap<String,Object> hashMap);

}
