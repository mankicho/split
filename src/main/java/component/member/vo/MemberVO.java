package component.member.vo;

import component.member.AuthDTO;
import lombok.Data;

import java.util.List;

@Data
public class MemberVO {
    private String email;
    private String pw;
    private String phoneNumber;
    private String sex;
    private String bornTime;
    private String nickname;
    private List<AuthDTO> authList;
}
