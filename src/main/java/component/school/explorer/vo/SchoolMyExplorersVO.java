package component.school.explorer.vo;

import component.school.vo.SchoolHashTags;
import lombok.Data;

import java.util.List;

@Data
public class SchoolMyExplorersVO {
    private int tid;
    private int schoolId;
    private int classId;
    private String schoolName;
    private int schoolType;
    private String nickname;
    private String imagePath;
    private int todayReservedPerson;
    private List<SchoolHashTags> hashTags;
}
