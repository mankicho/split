package component.school.explorer.dto;

import lombok.Data;

@Data
public class SchoolExplorerDTO {
    private int schoolId; // 학교 id
    private int classId; // 클래스 id
    private int weekday; // 요일
    private String selectedDate; // 선택날짜

//    private int classId;
//    private String selectedDate;
//    private int weekday;
//    private String memberEmail;

}
