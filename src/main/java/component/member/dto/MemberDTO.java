package component.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDTO {
    @NotNull
    private String email;
    @NotNull
    private String pw;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String sex;
    @NotNull
    private String bornTime;

    @Size(min = 2, max = 8, message = "닉네임은 2자이상 8자 이하로 해주세요.")
    private String nickname;
}

