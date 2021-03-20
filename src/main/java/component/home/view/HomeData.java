package component.home.view;

import lombok.Data;

@Data
public class HomeData {
    private String memberEmail; // 유저 이메일
    private int num; // 유저의 총 출석체크 회수
    private int schoolId; // 학교 Id
    private int classId; // 클래스 Id
    private String schoolName; // 학교 이름
    private String imagePath; // 학교 사진
    private int allNums; // 총 이용자 수
}
