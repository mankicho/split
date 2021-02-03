package component.zone;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ZoneDAO {

    @Setter(onMethod_ = {@Autowired})
    private ZoneMapper zoneMapper;

    public List<ZoneVO> selectZones(double lat, double lng, String type) {
        return zoneMapper.selectZones(lat, lng, type);
    }

}
