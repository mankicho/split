package component.school.explorer.vo;

import lombok.Data;

@Data
public class SchoolTestVO {
    private int schoolId;
    private int sum; // 누적 출석인원
    private int avg; // 평균 출석인원
    private int days; // 설립 기간
    private int sumAmount; // 누적 보증금
    private int avgAmount; // 하루평균 보증금
}
