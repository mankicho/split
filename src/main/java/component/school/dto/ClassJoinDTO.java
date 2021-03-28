package component.school.dto;

import lombok.Data;


@Data
public class ClassJoinDTO {
    private int schoolId; // 스쿨 Id
    private int classId; // 클래스 Id
    private int setDay; // 설정 요일(bit 값)
    private String memberEmail; // 유저 이메일
    private String startDate; // 시작 날짜
    private String endDate; // 끝 날짜
    private int cnt; // 총 몇일
    private int setPaymentAmount; // 설정 결제요금
    private int type; // 공식? 비공식?
}
