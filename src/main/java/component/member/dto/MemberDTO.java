package component.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String email;
    private String pw;
    private String phoneNumber;
    private String sex;
    private String bornTime;
    private String nickname;
}

