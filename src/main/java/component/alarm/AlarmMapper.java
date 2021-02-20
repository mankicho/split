package component.alarm;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlarmMapper {
    List<AlarmVO> getAlarms(@Param("email") String email);

    int saveAlarms(AlarmDTO alarmDTO);

    int updateReadFlag(List<AlarmVO> alarmVOS);

    int updateCheckFlag(AlarmVO alarmVO);
}
