package component.zone;

import component.zone.vo.ZoneLatLngVO;
import component.zone.vo.ZoneVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZoneMapper {
    List<ZoneVO> selectZones(); // 특정 위치 반경의 존 가져오기

    int recordSearching(@Param("email") String email, @Param("words") String words);

    int isExist(@Param("code") String code);

    List<String> autoComplete();

    ZoneLatLngVO getZone(@Param("planetCode") String planetCode);
}
