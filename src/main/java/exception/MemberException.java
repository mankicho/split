package exception;

import component.member.view.DefaultMemberErrorView;
import exception.error.DefaultErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Log4j2
public class MemberException {

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public DefaultMemberErrorView handleNullPointerException() {
        DefaultMemberErrorView view = new DefaultMemberErrorView();

        DefaultErrorCode code = DefaultErrorCode.NullPointer;
        view.setCode(code.getCode());
        view.setMsg(code.getMsg());
        return view;
    }
}
