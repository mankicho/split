package component.member.response;

import component.member.response.enumm.RegisterMemberStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class RegisterMemberResponse {
    private int registerStatus;
    private int fileUploadStatus;

    public RegisterMemberResponse(RegisterMemberStatus status) {
        this.registerStatus = status.getRegisterStatus();
        this.fileUploadStatus = status.getFileStatus();
    }
}
