package component.home;

import component.home.dto.HomeDataDTO;
import component.home.dto.HomeTicketDTO;
import component.home.response.HomeData;
import component.home.vo.HomeDataMyInfo;
import component.home.vo.HomeExplorerVO;
import component.home.vo.HomeTicketVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class HomeDataService {

    private final HomeDataMapper homeDataMapper;

    public HomeData getHomeData(HomeDataDTO homeDataDTO) {
        return homeDataMapper.getHomeData(homeDataDTO);
    }

    public HomeDataMyInfo getMyHomeInfo(HomeDataDTO homeData) {
        return homeDataMapper.getMyHomeInfo(homeData);
    } // 메인페이지의 나의 출석횟수, 나의상금, 총 우주탐험 수

    public List<HomeExplorerVO> getMyHomeExplorers(HomeDataDTO homeData) {
        return homeDataMapper.getMyHomeExplorers(homeData);
    }

    ; // 나의 탐험단 카드

    public HomeTicketVO getTicket(HomeTicketDTO homeTicketDTO) {
        return homeDataMapper.getTicket(homeTicketDTO);
    }
}
