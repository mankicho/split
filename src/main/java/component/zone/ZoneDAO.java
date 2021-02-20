package component.zone;

import lombok.Setter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ZoneDAO {

    @Setter(onMethod_ = {@Autowired})
    private ZoneMapper zoneMapper;

    public List<ZoneVO> selectZones() {
        return zoneMapper.selectZones();
    }

    public int isExist(String code) {
        return zoneMapper.isExist(code);
    }

    public List<String> autoComplete() {
        return zoneMapper.autoComplete();
    }
}
