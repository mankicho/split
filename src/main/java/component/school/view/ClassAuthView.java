package component.school.view;

import component.home.vo.HomeTicketVO;
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

    private HomeTicketVO homeTicketVO;

    public ClassAuthView(SchoolErrorCode code) {
        this.status = code.getStatus();
        this.msg = code.getMsg();
        this.authenticatedRow = -1;
    }

}
