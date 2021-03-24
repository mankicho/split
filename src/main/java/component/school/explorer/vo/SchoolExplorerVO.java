package component.school.explorer.vo;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SchoolExplorerVO {
    private List<SchoolExplorerAttendanceListVO> schoolExplorerAttendanceListVOS;
    private SchoolClassAvgAttendanceRateVO schoolClassAvgAttendanceRateVO;
}
