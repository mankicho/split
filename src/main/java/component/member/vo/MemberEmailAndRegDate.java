package component.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class MemberEmailAndRegDate {
    private String email;
    private Timestamp regDate;
}
