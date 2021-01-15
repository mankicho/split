package component.plan;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Setter(onMethod_ = {@Autowired})
    private PlanDAO planDAO;

    @Override
    public int insertPlan(PlanDTO planDTO) {
        return planDAO.insertOfficialPlan(planDTO);
    }

    @Override
    public int deletePlan(int planLogId) {
        return planDAO.deleteOfficialPlan(planLogId);
    }

    @Override
    public PlanVO selectByIdAndEmail(HashMap<String, Object> hashMap) {
        return planDAO.selectByIdAndEmail(hashMap);
    }

    @Override
    public List<PlanVO> selectsAllPlans(String email) {
        return planDAO.selectsAllPlans(email);
    }

    @Override
    public int deleteRangePlan(HashMap<String, String> requestBody) {
        return planDAO.deleteRangePlan(requestBody);
    }
}
