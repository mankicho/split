package component.plan.off;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OfficialPlanServiceImpl implements OfficialPlanService {

    @Setter(onMethod_ = {@Autowired})
    private OfficialPlanDAO officialPlanDAO;

    @Override
    public List<OfficialPlanVO> selects() {
        return officialPlanDAO.selects();
    }

    @Override
    public int insertOfficialPlan(OfficialPlanDTO officialPlanDTO) {
        return officialPlanDAO.insertOfficialPlan(officialPlanDTO);
    }

    @Override
    public int deleteOfficialPlan(HashMap<String, Object> hashMap) {
        return officialPlanDAO.deleteOfficialPlan(hashMap);
    }

    @Override
    public OfficialPlanDTO selectById(HashMap<String, Object> hashMap) {
        return null;
    }
}
