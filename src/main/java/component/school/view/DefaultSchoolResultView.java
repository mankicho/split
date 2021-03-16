package component.school.view;

import exception.error.SchoolErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultSchoolResultView {
    private int status;
    private int insertedRow;
    private String message;

    public DefaultSchoolResultView(SchoolErrorCode code) {
        this.status = code.getStatus();
        this.message = code.getMsg();
    }
}
