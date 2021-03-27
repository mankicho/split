package component.home.view;

import component.home.vo.HomeDataMyInfo;
import component.home.vo.HomeExplorerVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class HomeData {
    private String memberEmail;
    private HomeDataMyInfo homeDataMyInfo;
    private List<HomeExplorerVO> homeExplorerVOS;
}
