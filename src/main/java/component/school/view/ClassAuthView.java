package component.school.view;

import exception.error.SchoolErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassAuthView {
    private int status;
    private int authenticatedRow;
    private String message;

    public ClassAuthView(SchoolErrorCode code) {
        this.status = code.getStatus();
        this.message = code.getMsg();
    }
}
