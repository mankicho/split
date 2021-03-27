package component.home;

import component.home.view.HomeData;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class HomeDataService {

    private final HomeDataMapper homeDataMapper;

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

    public HomeData getHomeData(HomeDataDTO homeDataDTO) {
        return homeDataMapper.getHomeData(homeDataDTO);
    }
}
