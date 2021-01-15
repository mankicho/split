package component.member;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class MemberTimer {
    private String email;
    private Time focusTime;
    private Date focusDate;
}
