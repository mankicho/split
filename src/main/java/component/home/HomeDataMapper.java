package component.home;

import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public interface HomeDataMapper {
    int insertPlanAuth(@Param("planId") int planId, @Param("planType") int planType);

    int selectPlanAuthLogsOfToday(); // 오늘 총 예약자가 총 몇명인가

    int selectPlanAuthLogsFor30Minutes(); // 30분 단위로 예약자가 총 몇명인가

    int selectPlanAuthLogsFor30MinutesOfSuccessUsers(); // 30분 단위로 인증한 유저가 총 몇명인가
}
