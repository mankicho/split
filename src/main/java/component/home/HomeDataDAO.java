package component.home;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class HomeDataDAO {

    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    public int insertPlanAuth(int planId, int planType) {
        HomeDataMapper homeDataMapper = sqlSession.getMapper(HomeDataMapper.class);
        return homeDataMapper.insertPlanAuth(planId, planType);
    }

    public int selectPlanAuthLogsOfToday() {
        HomeDataMapper homeDataMapper = sqlSession.getMapper(HomeDataMapper.class);
        return homeDataMapper.selectPlanAuthLogsOfToday();
    }

    public int selectPlanAuthLogsFor30Minutes(HashMap<String, String> hashMap) {
        HomeDataMapper homeDataMapper = sqlSession.getMapper(HomeDataMapper.class);
        return homeDataMapper.selectPlanAuthLogsFor30Minutes(hashMap);
    }

    public int selectPlanAuthLogsFor30MinutesOfSuccessUsers(String fromTime, String toTime) {
        HomeDataMapper homeDataMapper = sqlSession.getMapper(HomeDataMapper.class);
        return homeDataMapper.selectPlanAuthLogsFor30MinutesOfSuccessUsers(fromTime, toTime);
    }
}
