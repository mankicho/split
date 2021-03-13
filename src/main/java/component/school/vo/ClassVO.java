package component.school.vo;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class ClassVO {
    private int classId;
//    private int schoolId;
//    private int weekdayType;
//    private Time authTime;
    private int allMembers;
    private int authMembers;
}
