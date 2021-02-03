package controller;

import component.zone.ZoneVO;
import component.zone.ZoneService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
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

    /**
     * get zones by 500M around certain point(distance can changed)
     *
     * @return
     */
    @GetMapping(value = "/get.do") // 지도에 표시할 공간 가져오기
    public List<ZoneVO> getZones(HttpServletRequest request) {
        String plainLat = request.getParameter("lat");
        String plainLng = request.getParameter("lng");
        String type = request.getParameter("type");
        type += '%';
        double lat = Double.parseDouble(plainLat);
        double lng = Double.parseDouble(plainLng);
        List<ZoneVO> list = zoneService.selectZones(lat, lng, type);

        for (int i = 0; i < 4; i++) {
            list.addAll(list);
        }
        return list;
    }
}
