package component.school.vo;

import lombok.Data;

// 특정 갤럭시, 특정 탐험단에 가입한 모든 유저
@Data
public class SchoolMembers {
    private String memberEmail;
    private String deviceType;
    private String deviceToken;
}
