package component.zone;

import component.zone.vo.ZoneVO;

import java.util.List;

public interface ZoneService {

    List<ZoneVO> selectZones(); // 특정 위치 반경의 존 가져오기

    int recordSearching(String email, String words);

    int isExist(String code);

    List<String> autoComplete();


}
