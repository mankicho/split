package component.member.vo;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Data
public class MemberDeviceVO {
    private String memberEmail;
    private int deviceType;
    private String deviceToken;
    private String schoolName;
}

