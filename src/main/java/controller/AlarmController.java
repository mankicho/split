package controller;

import component.alarm.AlarmService;
import component.alarm.AlarmVO;
import component.alarm.dto.DeleteAlarmDTO;
import component.alarm.response.AlarmResponse;
import component.alarm.response.AlarmResponseType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/split/alarm")
@RequiredArgsConstructor
@Log4j2
public class AlarmController {

    private final AlarmService alarmService;


    // 개인의 알림 다 가져오기
    @PostMapping("/get.do")
    public List<AlarmVO> getAlarms(@RequestParam("email") String email) {
        return alarmService.getAlarms(email);
    }

    // 알림을 받았음을 표시하기
    @PostMapping("/update/read/flag")
    public AlarmResponse updateReadFlag(@RequestBody List<Integer> idArr) {
        return alarmService.updateReadFlag(idArr);
    }

    @PostMapping(value = "/delete.do")
    public AlarmResponse deleteAlarm(@RequestBody DeleteAlarmDTO deleteAlarmDTO) {
        return alarmService.deleteAlarm(deleteAlarmDTO);
    }
}
