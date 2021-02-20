package component.alarm;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmServiceImpl implements AlarmService {
    @Override
    public List<AlarmVO> getAlarms(String email) {
        return null;
    }

    @Override
    public int saveAlarms(AlarmDTO alarmDTO) {
        return 0;
    }

    @Override
    public int updateReadFlag(List<AlarmVO> alarmVOS) {
        return 0;
    }

    @Override
    public int updateCheckFlag(AlarmVO alarmVO) {
        return 0;
    }
}
