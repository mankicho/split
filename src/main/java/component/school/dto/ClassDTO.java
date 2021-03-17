package component.school.dto;

import lombok.Data;

@Data
public class ClassDTO {
    private int schoolId;
    private int weekday;
    private String selectedDate;
}
