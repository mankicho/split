package component.plan.nonoff;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NonOfficialPlanServiceImpl implements NonOfficialPlanService {

    @Setter(onMethod_ = {@Autowired})
    private NonOfficialPlanDAO nonOfficialPlanDAO;

    @Override
    public int insertNonOfficialPlan(NonOfficialPlanDTO nonOfficialPlanDTO) {
        return nonOfficialPlanDAO.insertNonOfficialPlan(nonOfficialPlanDTO);
    }
}
