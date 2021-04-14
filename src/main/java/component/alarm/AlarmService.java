package component.alarm;

import component.alarm.dto.DeleteAlarmDTO;
import component.alarm.response.AlarmResponse;
import component.alarm.response.AlarmResponseType;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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


    public AlarmResponse updateReadFlag(List<Integer> idArrList) {
        if (idArrList == null || idArrList.isEmpty()) {
            AlarmResponseType responseType = AlarmResponseType.NoDataToUpdate;
            return AlarmResponse.builder().status(responseType.getStatus()).msg(responseType.getMsg()).build();
        }
        int[] arr = idArrList.stream().mapToInt(i -> i).toArray();
        // read flag update
        int updatedRow = alarmMapper.updateReadFlag(arr);
        //
        AlarmResponseType responseType;
        if (updatedRow == 0) {
            responseType = AlarmResponseType.ServerError;
        } else {
            responseType = AlarmResponseType.SuccessFulUpdateData;
        }
        return AlarmResponse.builder().status(responseType.getStatus()).msg(responseType.getMsg()).build();
    }

    public int saveAlarms(AlarmDTO alarmDTO) {
        return alarmMapper.saveAlarms(alarmDTO);
    }

    public AlarmResponse deleteAlarm(DeleteAlarmDTO deleteAlarmDTO) {
        List<AlarmVO> alarmVOS = alarmMapper.getAlarms(deleteAlarmDTO.getMemberEmail());

        boolean hasAlarm = false;
        for (AlarmVO alarmVO : alarmVOS) {
            if (alarmVO.getAlarmId() == deleteAlarmDTO.getAlarmId()) {
                hasAlarm = true;
                break;
            }
        }

        if (!hasAlarm) {
            AlarmResponseType type = AlarmResponseType.DontHaveTheAlarm;
            return AlarmResponse.builder().status(type.getStatus()).msg(type.getMsg()).build();
        }
        int deletedRow = alarmMapper.deleteAlarm(deleteAlarmDTO.getAlarmId());

        AlarmResponseType responseType;
        if (deletedRow == 0) {
            responseType = AlarmResponseType.ServerError;
        } else {
            responseType = AlarmResponseType.SuccessFulDeleteData;
        }

        return AlarmResponse.builder().status(responseType.getStatus()).msg(responseType.getMsg()).build();
    }
}
