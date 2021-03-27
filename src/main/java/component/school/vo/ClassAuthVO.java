package component.school.vo;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class ClassAuthVO {
    private int schoolId;
    private int classId;
    private int diff; // 인증 설정시간과 현재시간의 차이
    private String setLocation; // 지정장소
}
