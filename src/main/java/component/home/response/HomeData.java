package component.home.response;

import component.home.vo.HomeDataMyInfo;
import component.home.vo.HomeExplorerVO;
import lombok.*;

import java.util.List;

@Getter
@ToString
@Builder
public class HomeData {
    private String memberEmail;
    private HomeDataMyInfo homeDataMyInfo;
    private List<HomeExplorerVO> homeExplorerVOS;
}
