package component.plan.nonoff;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class NonOfficialPlanServiceImpl implements NonOfficialPlanService {

    @Setter(onMethod_ = {@Autowired})
    private NonOfficialPlanDAO nonOfficialPlanDAO;

    @Override
    public int insertNonOfficialPlan(NonOfficialPlanDTO nonOfficialPlanDTO) {
        return nonOfficialPlanDAO.insertNonOfficialPlan(nonOfficialPlanDTO);
    }

    @Override
    public List<NonOfficialPlanDTO> getNonPlan(HashMap<String, Object> hashMap) {
        return nonOfficialPlanDAO.getNonPlan(hashMap);
    }

    @Override
    public int deleteNonOfficialPlan(HashMap<String, Object> hashMap) {
        return nonOfficialPlanDAO.deleteNonOfficialPlan(hashMap);
    }

    @Override
    public NonOfficialPlanDTO nonOfficialPlanShare(int nonOfficialPlanLogId) {
        return nonOfficialPlanDAO.nonOfficialPlanShare(nonOfficialPlanLogId);
    }

}
