package component.home;

import component.home.view.HomeData;
import component.home.vo.HomeDataMyInfo;
import component.home.vo.HomeExplorerVO;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public interface HomeDataMapper {
    int selectUsersTotalCheckTime(@Param("email") String email);

    HomeData getHomeData(HomeDataDTO homeDataDTO);

    HomeDataMyInfo getMyHomeInfo(HomeDataDTO homeData); // 메인페이지의 나의 출석횟수, 나의상금, 총 우주탐험 수

    List<HomeExplorerVO> getMyHomeExplorers(HomeDataDTO homeData); // 나의 탐험단 카드


}
