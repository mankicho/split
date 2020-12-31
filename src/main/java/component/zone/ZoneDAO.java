package component.zone;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ZoneDAO {

    @Setter(onMethod_ = {@Autowired})
    private ZoneMapper zoneMapper;

    public List<ZoneDTO> selectZones(double lat, double lng) {
        return zoneMapper.selectZones(lat, lng);
    }

}
