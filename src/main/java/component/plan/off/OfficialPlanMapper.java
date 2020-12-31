package component.plan.off;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.HashMap;
import java.util.List;

public interface OfficialPlanMapper {
    List<OfficialPlanVO> selects();

    int insertOfficialPlan(OfficialPlanDTO officialPlanDTO);

    int deleteOfficialPlan(HashMap<String, Object> hashMap);

    int selectByIdAndEmail(HashMap<String, Object> hashMap);

}

