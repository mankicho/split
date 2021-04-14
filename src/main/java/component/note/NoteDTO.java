package component.note;

import lombok.*;

// builder 패턴
@AllArgsConstructor
@NoArgsConstructor
//@Setter
@Getter
@ToString
@Builder
public class NoteDTO {
    private String noteUUID;
    private String fromEmail;
    private String toEmail;
    private String content;
}
