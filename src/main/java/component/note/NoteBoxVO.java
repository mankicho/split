package component.note;

import lombok.Data;

import java.util.Date;

@Data
public class NoteBoxVO {
    private int noteBoxId;
    private String fromEmail;
    private String nickname;
    private String noteBoxUUID;
    private int deleteNoteId;
    private String content;
    private Date noteTime;
    private int notRead;
}
