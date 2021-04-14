package component.school.explorer.vo;

import lombok.Data;

@Data // 탐험단 - 상금
public class SchoolRewardVO {
    private int accAuthMembers; // 모든 갤럭시 누적 출석인원
    private double avgAttRate; // 모든 갤럭시 평균 출석률
    private int avgAttMembers; // 모든 갤럭시 평균 출석인원
    private int todayAllAmount; // 모든 갤럭시 오늘 보증금
    private int allAccAmount; // 모든 갤럭시 누적보증금
    private int todayDailyAmount; // 모든 갤럭시 하루평균 보증금
    private int specialAccAuthMembers; // 특정 갤럭시 누적 출석인원
    private double specialAvgAttRate; // 특정 갤럭시 평균 출석률
    private int specialAvgAttMembers; // 특정 갤럭시 평균 출석 인원
    private int specialAllAccAmount; // 특정 갤럭시 누적보증금
    private int specialTodayDailyAmount; //특정 갤럭시 하루평균 보증금
    private int specialTodayAllAmount; // 특정 갤럭시 오늘 보증금
    private int predictReward;


}
