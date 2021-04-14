package component.school.dto;

import lombok.*;


@Builder
@Setter
@Getter
@ToString
public class ClassAuthLogDTO {
    private int schoolId;
    private int tid;
    private int classId;
    private String memberEmail;
    private String planetCode;
}
