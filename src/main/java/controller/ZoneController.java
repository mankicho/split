package controller;

import component.zone.vo.ZoneVO;
import component.zone.ZoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * for bringing zone(normal cafe, study cafe, library and so on) data
 */
@RestController
@RequestMapping(value = "/split/zone")
@Log4j
@RequiredArgsConstructor
public class ZoneController {

    @ExceptionHandler({
            NumberFormatException.class
    })
    public HashMap<String, Object> handleNumberFormatException(NumberFormatException e) {
        log.error("error", e);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("error", e.getClass().getName());
        hashMap.put("message", e.getMessage());
        return hashMap;
    }

    private final ZoneService zoneService; // 지도 DB 에 접근하는 Service

    /**
     * get zones by 500M around certain point(distance can changed)
     *
     * @return
     */
    @GetMapping(value = "/get.do") // 지도에 표시할 공간 가져오기
    public List<ZoneVO> getZones() {
        return zoneService.selectZones();
    }

    @GetMapping(value = "/check/exist")
    public int isExist(@RequestParam("code") String code) {
        return zoneService.isExist(code);
    }

    @GetMapping(value = "/auto/complete")
    public HashMap<String, Object> autoComplete() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("words", zoneService.autoComplete());
        return hashMap;
    }
}
