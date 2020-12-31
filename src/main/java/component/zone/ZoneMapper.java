package component.zone;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ZoneMapper {
    List<ZoneDTO> selectZones(@Param("lat") double lat, @Param("lng") double lng); // 특정 위치 반경의 존 가져오기

    int recordSearching(@Param("email") String email, @Param("words") String words);
}
