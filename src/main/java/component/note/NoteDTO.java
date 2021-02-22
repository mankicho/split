package component.note;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class NoteDTO {
    private String fromEmail;
    private String toEmail;
    private String content;
}
