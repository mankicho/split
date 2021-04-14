package component.home.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HomeTicketVO {
    private int schoolType; // 학교 유형
    private String schoolName; // 학교 이름
    private int cnt; // 총 출석해야하는 횟수
    private int allNum; // 내가 출석한 횟수
    private String authTime; // 인증해야할 시간
    private int dust; // 더스트
    private int myPaymentAmount; // 내가 회수한 보증금
    private int allPaymentAmount; //  총 보증금
    private int myPoint; // 내가 획득한 상금
    private int mannerTime; // 매너시간
    private int setPaymentAmount; // 보증금
    private int predictReward; // 예상상금
}
