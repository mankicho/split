package component.home.vo;

import lombok.Data;

import java.util.Date;

@Data
public class HomeExplorerVO {
    private String memberEmail;
    private int schoolId;
    private int classId;
    private int cnt; // 총 출석해야 하는 횟수
    private int setPaymentAmount; // 설정 보증금
    private int attNum; // 출석한 횟수
    private int schoolType; // 공식 , 비공식
    private String schoolName; // 갤럭시 이름
    private String authTime; // 출석 시간
    private int mySumPoint; // 누적 상금
}
