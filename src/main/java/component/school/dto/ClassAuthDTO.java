package component.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassAuthDTO {
    private int schoolId;
    private int classId;
    private String planetCode;
    private String memberEmail;
}
