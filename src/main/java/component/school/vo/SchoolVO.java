package component.school.vo;

import lombok.Data;

import java.util.List;

@Data
public class SchoolVO {
    private int categoryId;
    private int schoolId;
    private String schoolName;
    private String setLocation;
    private String oneLineMessage;
    private String imagePath;
    private int allMembers;
    private int sumPerson;
    private double avgPerson;
    private List<SchoolHashTags> hashTags;
}
