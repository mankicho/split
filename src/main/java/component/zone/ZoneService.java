package component.zone;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZoneService {

    List<ZoneVO> selectZones(String type); // 특정 위치 반경의 존 가져오기

    int recordSearching(String email, String words);

    int isExist(String code);

}
