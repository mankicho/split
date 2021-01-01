package controller;

import component.zone.ZoneDTO;
import component.zone.ZoneService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * for bringing zone(normal cafe, study cafe, library and so on) data
 */
@ControllerAdvice
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
        hashMap.put("error", "numberFormatException");
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
    @GetMapping(value = "/get.do")
    public List<ZoneDTO> getZones(HttpServletRequest request) {
        String plainLat = request.getParameter("lat");
        String plainLng = request.getParameter("lng");

        double lat = Double.parseDouble(plainLat);
        double lng = Double.parseDouble(plainLng);
        return zoneService.selectZones(lat, lng);
    }
}
