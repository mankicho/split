package component.member;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDTO {
    private String email;
    private String pw;
    private String phoneNumber;
    private String sex;
    private String bornTime;
    private String nickname;
    private List<AuthDTO> authList;

    public MemberDTO(String email, String pw, String phoneNumber, String sex, String bornTime, String nickname) {
        this.email = email;
        this.pw = pw;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.bornTime = bornTime;
        this.nickname = nickname;
    }
}

