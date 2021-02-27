package component.school.vo;

import lombok.Data;

import java.util.List;

@Data
public class SchoolVO {
    private int schoolId;
    private int categoryId;
    private String memberEmail;
    private String schoolName;
    private String setLocation;
    private String oneLineMessage;
    private int allMembers;
    private String imagePath;

    private List<SchoolHashTags> hashTags;
}
