package component.plan.auth;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanAuthServiceImpl implements PlanAuthService {

    @Setter(onMethod_ = {@Autowired})
    private PlanAuthDAO planAuthDAO;

    @Override
    public int planAuth(int planId, int planType) {
        return planAuthDAO.planAuth(planId, planType);
    }
}
