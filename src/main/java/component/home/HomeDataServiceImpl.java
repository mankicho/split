package component.home;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeDataServiceImpl implements HomeDataService {

    @Setter(onMethod_ = {@Autowired})
    private HomeDataDAO homeDataDAO;

    @Override
    public int insertPlanAuth(int planId, int planType) {
        return homeDataDAO.insertPlanAuth(planId, planType);
    }

    @Override
    public int selectPlanAuthLogsOfToday() {
        return homeDataDAO.selectPlanAuthLogsOfToday();
    }

    @Override
    public int selectPlanAuthLogsFor30Minutes() {
        return homeDataDAO.selectPlanAuthLogsFor30Minutes();
    }

    @Override
    public int selectPlanAuthLogsFor30MinutesOfSuccessUsers() {
        return homeDataDAO.selectPlanAuthLogsFor30MinutesOfSuccessUsers();
    }
}
