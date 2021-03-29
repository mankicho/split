package component.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassAuthLogDTO {
    private int schoolId;
    private int classId;
    private String memberEmail;
    private String qrCode;
}
