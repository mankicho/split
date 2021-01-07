package component.plan.nonoff;

import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public interface NonOfficialPlanService {
    int insertNonOfficialPlan(NonOfficialPlanDTO nonOfficialPlanDTO);

    List<NonOfficialPlanDTO> getNonPlan(HashMap<String, Object> hashMap);

    int deleteNonOfficialPlan(HashMap<String, Object> hashMap);

    NonOfficialPlanDTO nonOfficialPlanShare(int nonOfficialPlanLogId);

    Integer selectByIdAndEmail(HashMap<String, Object> hashMap);

}
