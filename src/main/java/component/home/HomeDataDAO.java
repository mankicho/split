package component.home;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class HomeDataDAO {

    @Setter(onMethod_ = {@Autowired})
    private HomeDataMapper homeDataMapper;

    public int selectPlanAuthLogsOfToday() {
        return homeDataMapper.selectPlanAuthLogsOfToday();
    }

    public int selectPlanAuthLogsFor30Minutes(HashMap<String, String> hashMap) {
        return homeDataMapper.selectPlanAuthLogsFor30Minutes(hashMap);
    }

    public int selectPlanAuthLogsFor30MinutesOfSuccessUsers(String fromTime, String toTime) {
        return homeDataMapper.selectPlanAuthLogsFor30MinutesOfSuccessUsers(fromTime, toTime);
    }

    public int selectUsersTotalCheckTime(String email) {
        return homeDataMapper.selectUsersTotalCheckTime(email);
    }
}
