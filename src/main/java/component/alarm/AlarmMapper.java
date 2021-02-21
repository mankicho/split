package component.alarm;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlarmMapper {
    List<AlarmVO> getAlarms(@Param("email") String email);

    int saveAlarms(AlarmDTO alarmDTO);

    int updateReadFlag(int[] idArr);

    int updateCheckFlag(AlarmVO alarmVO);

    List<AlarmVO> getAlarmsByAlarmId(int[] idArr);
}
