package component.plan.off;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficialPlanServiceImpl implements OfficialPlanService {

    @Setter(onMethod_ = {@Autowired})
    private OfficialPlanDAO officialPlanDAO;

    @Override
    public List<OfficialPlanDTO> selects() {
        return officialPlanDAO.selects();
    }
}
