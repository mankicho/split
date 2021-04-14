package component.school.explorer.dto;

import lombok.Data;

@Data
public class SchoolExplorerRewardDTO {
    private int schoolId;
    private int classId; // 클래스 id
    private int weekday; // 요일 숫자
    private String memberEmail; // 유저 이메일
    private String selectedDate; // 선택된 날짜
}
