package component.alarm;

import lombok.Data;

import java.sql.Date;

/**
 * alarm_message table (
 * message_id,to_email,from_email,title,message,message_seq,check_flag(default false),transfertime(default now())
 */
@Data
public class AlarmMessageDTO {
    private int messageId;
    private String toEmail;
    private String fromEmail;
    private String title;
    private String message;
    private int messageSeq;
    private boolean checkFlag;
    private Date transferTime;

    public AlarmMessageDTO(String toEmail, String fromEmail, String title,
                           String message) {
        this.toEmail = toEmail;
        this.fromEmail = fromEmail;
        this.title = title;
        this.message = message;
    }
}
