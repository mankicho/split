package exception.error;

import exception.view.DefaultErrorView;
import lombok.Getter;

@Getter
public enum TokenErrorCode {
    IllegalArgumentError(600, "JWT String argument cannot be null or empty");
    private int status;
    private String msg;

    TokenErrorCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }


}
