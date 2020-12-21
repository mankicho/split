package component.home;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public int selectPlanAuthLogsFor30Minutes() {
        HomeDataMapper homeDataMapper = sqlSession.getMapper(HomeDataMapper.class);
        return homeDataMapper.selectPlanAuthLogsFor30Minutes();
    }

    public int selectPlanAuthLogsFor30MinutesOfSuccessUsers() {
        HomeDataMapper homeDataMapper = sqlSession.getMapper(HomeDataMapper.class);
        return homeDataMapper.selectPlanAuthLogsFor30MinutesOfSuccessUsers();
    }
}
