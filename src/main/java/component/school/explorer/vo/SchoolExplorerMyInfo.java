package component.school.explorer.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SchoolExplorerMyInfo {
    private int cnt; // 총 출석횟수
    private int allAttNum; // 내가 출석한 횟수
    private int allPaymentAmount; // 전체 보증금
    private int myPaymentAmount; // 내가 얻은 보증금
    private int myPoint; // 내 상금
    private Date date; // 출석 요일
    private int dust; // 더스트
}
