package component.note;

import lombok.Data;

import java.sql.Date;

@Data
public class NoteVO {
    private int noteId;
    private String noteUUID;
    private String fromEmail;
    private String toEmail;
    private String content;
    private Date noteTime;
}
