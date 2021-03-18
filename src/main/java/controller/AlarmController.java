package controller;

import component.alarm.AlarmDTO;
import component.alarm.AlarmService;
import component.alarm.AlarmVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLSyntaxErrorException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/split/alarm")
@RequiredArgsConstructor
@Log4j2
public class AlarmController {

    private final AlarmService alarmService;

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public int exceptionHandler() {
        return 0;
    }

    // 개인의 알림 다 가져오기
    @PostMapping(value = "/get.do")
    public List<AlarmVO> getAlarms(HttpServletRequest request) {
        String email = request.getParameter("email");
        return alarmService.getAlarms(email);
    }

    // 알림을 받았음을 표시하기
    @PostMapping(value = "/update/read/flag")
    public int updateReadFlag(@RequestBody List<Integer> idArr) {
        int[] arr = idArr.stream().mapToInt(i -> i).toArray();
        log.info(Arrays.toString(arr));
        int updatedRow = alarmService.updateReadFlag(arr);
        log.info(updatedRow);
        return updatedRow;
    }

    @PostMapping(value = "/insert.do")
    public int saveAlarms(@RequestBody AlarmDTO alarmDTO) {
        log.info(alarmDTO);
        return alarmService.saveAlarms(alarmDTO);
    }

    @GetMapping(value = "/delete.do")
    public int deleteAlarm(@RequestParam("alarmId") int alarmId) {
        return alarmService.deleteAlarm(alarmId);
    }
}
