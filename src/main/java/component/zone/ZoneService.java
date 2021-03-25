package component.zone;

import component.zone.vo.ZoneLatLngVO;
import component.zone.vo.ZoneVO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneMapper zoneMapper;

    public List<ZoneVO> selectZones() {
        return zoneMapper.selectZones();
    }

    public int recordSearching(String email, String words) {
        return 0;
    }

    public int isExist(String code) {
        return zoneMapper.isExist(code);
    }

    public List<String> autoComplete() {
        return zoneMapper.autoComplete();
    }

}
