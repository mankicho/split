package exception.view;

import exception.error.SchoolErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultErrorView {
    private int status;
    private String message;

    public DefaultErrorView(SchoolErrorCode code) {
        this.status = code.getStatus();
        this.message = code.getMsg();
    }
}
