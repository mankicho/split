package component.member.friend;

import lombok.Data;

import java.sql.Date;

@Data
public class FriendAddRequestVO {
    private int rid;
    private String fromEmail;
    private String toEmail;
    private boolean checkFlag;
    private Date transDatetime;
}
