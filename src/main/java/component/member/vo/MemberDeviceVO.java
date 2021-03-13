package component.member.vo;

import lombok.Data;

@Data
public class MemberDeviceVO {
    private String memberEmail;
    private int deviceType;
    private String deviceToken;
    private String schoolName;
}

