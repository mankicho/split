package controller;

import component.plan.PlanService;
import component.plan.PlanVO;
import component.zone.ZoneVO;
import component.zone.ZoneService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * for bringing zone(normal cafe, study cafe, library and so on) data
 */
@RestController
@RequestMapping(value = "/split/zone")
@Log4j
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

    /**
     * communicate with zone DB Object
     */
    @Setter(onMethod_ = {@Autowired})
    private ZoneService zoneService;

    @Setter(onMethod_ = {@Autowired})
    private PlanService planService;

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

    @GetMapping(value = "/all/plan/at/certain/zone")
    public List<PlanVO> selectsAllPlansAtCertainZone(@RequestParam("placeSetting") String placeSetting) {
        return planService.selectsAllPlansAtCertainZone(placeSetting);
    }

    @GetMapping(value = "/auto/complete")
    public List<String> autoComplete(@Param("word") String word) {
        return zoneService.autoComplete(word);
    }
}
