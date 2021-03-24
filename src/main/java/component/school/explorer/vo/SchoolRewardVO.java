package component.school.explorer.vo;

import lombok.Data;

@Data // 탐험단 - 상금
public class SchoolRewardVO {
    private int schoolId; // 학교 id
    private int allAccPayment; // 모든 갤럭시 누적보증금
    private int allDailyPayment; // 모든 갤럭시 하루평균 보증금
    private int specialAccPayment; // 특정 갤럭시 누적보증금
    private int specialDailyPayment; //특정 갤럭시 하루평균 보증금
    private double allAvgAttendance; // 모든 갤럭시 평균 출석률
    private int allAccAttendance; // 모든 갤럭시 누적 출석인원
    private double specialAvgAttendance; // 특정 갤럭시 평균 출석률
    private int specialAccAttendance; // 특정 갤럭시 누적 출석인원
    private int allTodayAmount; // 모든 갤럭시 오늘 보증금
    private int specialTodayAmount; // 특정 갤럭시 오늘 보증금

    private int predictReward;


}
