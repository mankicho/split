package component.school.vo;

import lombok.Data;

import java.util.List;

@Data
public class SchoolVO {
    private int schoolId;
    private int categoryId;
    private String memberEmail;
    private String schoolName;
    private String oneLineMessage;
    private String imagePath;
    private String planetName;
    private int allMembersInSchool;

    private List<SchoolHashTags> hashTags;
}
