package component.school.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class SchoolDTO {
    private int schoolId;
    private int categoryId;
    private String memberEmail;
    private String schoolName;
    private String setLocation;
    private String oneLineMessage;
    private List<String> hashtags;
}
