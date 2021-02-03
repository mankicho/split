package component.zone;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Setter(onMethod_ = {@Autowired})
    private ZoneDAO zoneDAO;

    /**
     * @param lat
     * @param lng bring zones by OOO meters around certain point
     * @return
     */
    @Override
    public List<ZoneVO> selectZones(double lat, double lng,String type) {
        return zoneDAO.selectZones(lat, lng,type);
    }

    /**
     * @param email
     * @param words record user's searching
     * @return
     */
    @Override
    public int recordSearching(String email, String words) {
        return 0;
    }
}
