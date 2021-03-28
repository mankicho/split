package component.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassAuthDTO {
    private String qrToken; // insert 용
    private long now;
    private int weekday;
    private String memberEmail; // 유저 토큰
    private double lat; // 유저 위도
    private double lng; // 유저 경도
    private String date;  // 오늘날짜 yyyy-MM-dd
}
