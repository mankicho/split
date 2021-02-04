package component.zone;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZoneMapper {
    List<ZoneVO> selectZones(@Param("lat") double lat, @Param("lng") double lng,@Param("type") String type); // 특정 위치 반경의 존 가져오기

    int recordSearching(@Param("email") String email, @Param("words") String words);

    int isExist(@Param("code") String code);
}
