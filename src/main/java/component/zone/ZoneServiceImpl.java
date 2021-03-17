package component.zone;

import component.zone.vo.ZoneVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Setter(onMethod_ = {@Autowired})
    private ZoneDAO zoneDAO;

    /**
     * @return
     */
    @Override
    public List<ZoneVO> selectZones() {
        return zoneDAO.selectZones();
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

    @Override
    public int isExist(String code) {
        return zoneDAO.isExist(code);
    }

    @Override
    public List<String> autoComplete() {
        return zoneDAO.autoComplete();
    }
}
