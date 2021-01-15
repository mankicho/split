package component.home;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class HomeDataServiceImpl implements HomeDataService {

    @Setter(onMethod_ = {@Autowired})
    private HomeDataDAO homeDataDAO;

    @Override
    public int selectPlanAuthLogsOfToday() {
        return homeDataDAO.selectPlanAuthLogsOfToday();
    }

    @Override
    public int selectPlanAuthLogsFor30Minutes(HashMap<String, String> hashMap) {
        return homeDataDAO.selectPlanAuthLogsFor30Minutes(hashMap);
    }

    @Override
    public int selectPlanAuthLogsFor30MinutesOfSuccessUsers(String fromTime, String toTime) {
        return homeDataDAO.selectPlanAuthLogsFor30MinutesOfSuccessUsers(fromTime, toTime);
    }

    @Override
    public int selectUsersTotalCheckTime(String email) {
        return homeDataDAO.selectUsersTotalCheckTime(email);
    }
}
