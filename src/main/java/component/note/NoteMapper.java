package component.note;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoteMapper {
    int saveNote(NoteDTO noteDTO);

    List<NoteVO> getNotes(@Param("to") String toEmail, @Param("from") String from);

    NoteVO getNoteByNoteDTO(NoteDTO noteDTO);
}
