package component.zone;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZoneService {

    List<ZoneDTO> selectZones(double lat, double lng); // 특정 위치 반경의 존 가져오기

    int recordSearching(String email, String words);
}
