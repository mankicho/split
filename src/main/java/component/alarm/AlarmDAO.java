package component.alarm;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlarmDAO {
    @Setter(onMethod_ = {@Autowired})
    private AlarmMapper alarmMapper;

    public List<AlarmVO> getAlarms(String email) {
        return alarmMapper.getAlarms(email);
    }


    public int saveAlarms(AlarmDTO alarmDTO) {
        return alarmMapper.saveAlarms(alarmDTO);
    }

    public int updateReadFlag(List<AlarmVO> alarmVOS) {
        return alarmMapper.updateReadFlag(alarmVOS);
    }

    public int updateCheckFlag(AlarmVO alarmVO) {
        return alarmMapper.updateCheckFlag(alarmVO);
    }
}
