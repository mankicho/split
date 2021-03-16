package component.school.vo;

import lombok.Data;

import java.util.List;

@Data
public class SchoolVO {
    private int categoryId; // 카테고리 Id
    private int schoolId; // 학교 Id
    private String schoolName; // 학교 이름
    private String setLocation; // 출석 장소
    private String oneLineMessage; // 한줄메세지
    private String imagePath; // 학교 대표 사진
    private int allMembers; // 총 재학생
    private int sumPerson; // 누적 출석인원
    private double avgPerson; // 하루 평균 출석인원
    private int cumulativeAmount; // 누적 출석금액
    private List<SchoolHashTags> hashTags; // 해시태그
}
