package component.school.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ClassDTO {
    @NotNull
    private int schoolId;

    @NotNull
    private int weekday;

    @NotBlank
    private String selectedDate;
}
