package component.school.explorer.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SchoolExplorerMyInfo {
    private int totalNum; // 총 출석횟수
    private int myAttendanceNum; // 내가 출석한 횟수
    private int allAmount; // 전체 보증금
    private int myPayment; // 내가 얻은 보증금
    private int memberPoint; // 내 상금
    private Date date; // 출석 요일
    private int dust; // 더스트
}
