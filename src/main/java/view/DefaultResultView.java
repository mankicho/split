package view;

import exception.error.DefaultErrorCode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DefaultResultView extends ResultView {
    protected int status;
    protected String msg;

    protected DefaultResultView(DefaultErrorCode code){
        this.status = code.getCode();
        this.msg = code.getMsg();
    }

    public DefaultResultView() {
    }
}
