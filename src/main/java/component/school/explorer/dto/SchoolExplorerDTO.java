package component.school.explorer.dto;

import lombok.Data;

@Data
public class SchoolExplorerDTO {
    private int schoolId; // 학교 id
    private int classId; // 클래스 id
    private int weekday; // 요일
}
