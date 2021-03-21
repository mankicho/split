package component.school.view;

import exception.error.SchoolErrorCode;
import lombok.*;
import view.DefaultResultView;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassAuthView extends DefaultResultView {

    private int authenticatedRow;

    public ClassAuthView(SchoolErrorCode code) {
        this.status = code.getStatus();
        this.msg = code.getMsg();
        this.authenticatedRow = 0;
    }

}
