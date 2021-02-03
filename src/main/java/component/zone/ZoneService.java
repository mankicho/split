package component.zone;

import java.util.List;

public interface ZoneService {

    List<ZoneVO> selectZones(double lat, double lng,String type); // 특정 위치 반경의 존 가져오기

    int recordSearching(String email, String words);
}
