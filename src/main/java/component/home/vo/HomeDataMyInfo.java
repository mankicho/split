package component.home.vo;

import lombok.Data;

@Data
public class HomeDataMyInfo {
    private String memberEmail;
    private int myPoint; // 나의 상금
    private int myAttNum; // 나의 출석 횟수
    private int allAttNum; // 총 우주탐험 숫자
    private String attImage; // 내 이미지
    private int dust; // 더스트
}

