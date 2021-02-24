package component.note;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NoteUUID {
    private String uuid;
    private List<NoteVO> notes;

}
