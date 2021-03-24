package component.school.explorer.vo;

import lombok.Data;

@Data
public class SchoolExplorerAttendanceListVO {
    private String nickname; // 유저 닉네임
    private int logId; // logId == 0 ? 비출석 : 출석
    private int num; // 출석회수
    private String image; // logId == 0 ? 비출석 이미지 : 출석 이미지
}
