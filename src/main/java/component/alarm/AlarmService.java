package component.alarm;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmMapper alarmMapper;

    public List<AlarmVO> getAlarms(String email) {
        return alarmMapper.getAlarms(email);
    }


    public int updateReadFlag(int[] idArr) {
        // read flag update
        alarmMapper.updateReadFlag(idArr);
        //
        List<AlarmVO> alarmVOS = alarmMapper.getAlarmsByAlarmId(idArr);
        boolean readAllFlag = true;
        for (AlarmVO alarmVO : alarmVOS) {
            if (!alarmVO.isReadFlag()) {
                readAllFlag = false;
                break;
            }
        }

        if (!readAllFlag) {
            return -1;
        } else {
            return 1;
        }
    }

    public int saveAlarms(AlarmDTO alarmDTO) {
        return alarmMapper.saveAlarms(alarmDTO);
    }

    public int deleteAlarm(int alarmId) {
        return alarmMapper.deleteAlarm(alarmId);
    }
}
