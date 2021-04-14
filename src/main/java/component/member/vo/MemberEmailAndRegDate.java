package component.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MemberEmailAndRegDate {
    private String email;
    // timestamp -> LocalDateTime
    private Timestamp regDate;
}
