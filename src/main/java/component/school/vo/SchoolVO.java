package component.school.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SchoolVO {
    private int categoryId; // 카테고리 Id
    private int schoolId; // 학교 Id
    private int schoolType; // 학교유형(공식? 비공식?)
    private String nickname; // 학교 설립자
    private String schoolName; // 학교 이름
    private String oneLineMessage; // 한줄메세지
    private String imagePath; // 학교 대표 사진
    private String profileImage; // 멤버 이미지
    private String setLocation; // 출석 장소
    private Date period; // 모집 기간
    private int todayPerson; // 오늘 예약자
    private int sumPerson; // 누적 출석인원
    private int attPerson; // 평균 출석인원
    private int totalPayment; // 누적보증금액
    private int dailyPayment; // 하루평균금액
    private int totalPaymentRank; // 누적보증금액 순위
    private int sumPersonRank; //누적출석인원 순위
    private int attPersonRank; // 평균출석인원 순위
    private List<SchoolHashTags> hashTags; // 해시태그
}
