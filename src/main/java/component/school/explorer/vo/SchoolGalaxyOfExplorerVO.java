package component.school.explorer.vo;

import component.school.vo.ClassVO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
public class SchoolGalaxyOfExplorerVO {
    private List<ClassVO> classVOS;
    private GalaxyStatisticVO galaxyStatisticVO;
}
