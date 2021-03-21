package exception.view;

import exception.error.DefaultErrorCode;
import view.DefaultResultView;

public class DefaultErrorView extends DefaultResultView {
    public DefaultErrorView(DefaultErrorCode code) {
        super(code);
    }

    public DefaultErrorView() {
        super();
    }

}
