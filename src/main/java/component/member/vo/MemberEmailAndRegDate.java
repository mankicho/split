package component.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MemberEmailAndRegDate {
    private String email;
    private Date regDate;
}
