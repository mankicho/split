package component.plan.auth;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PlanAuthServiceImpl implements PlanAuthService {

    @Setter(onMethod_ = {@Autowired})
    private PlanAuthDAO planAuthDAO;

    @Override
    public int planAuthLog(HashMap<String, Object> hashMap) {
        System.out.println("planAuth");
        return planAuthDAO.attendant(hashMap);
    }
}

