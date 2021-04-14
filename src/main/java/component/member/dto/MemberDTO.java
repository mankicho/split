package component.member.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class MemberDTO {
    private String email;
    private String pw;
    private String phoneNumber;
    private String sex;
    private String bornTime;
    private String nickname;
}

