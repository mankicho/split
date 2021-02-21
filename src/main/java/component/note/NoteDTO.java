package component.note;

import lombok.Data;

@Data
public class NoteDTO {
    private String fromEmail;
    private String toEmail;
    private String content;
}
