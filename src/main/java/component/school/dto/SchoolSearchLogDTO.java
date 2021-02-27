package component.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SchoolSearchLogDTO {
    private String searchKeyword;
    private int num;

}
