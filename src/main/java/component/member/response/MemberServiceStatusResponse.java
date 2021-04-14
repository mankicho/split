package component.member.response;

import lombok.*;

@Getter
@Setter
@ToString
public class MemberServiceStatusResponse {
    private int status;

    private String msg;
}
