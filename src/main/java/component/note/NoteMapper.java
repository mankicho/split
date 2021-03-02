package component.note;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface NoteMapper {
    int saveNote(NoteDTO noteDTO); // 쪽지 저장

    String getUUID(@Param("toEmail") String toEmail, @Param("fromEmail") String fromEmail,
                   @Param("toEmail") String fromEmail2, @Param("fromEmail") String toEmail2);

    int createNoteBox(@Param("toEmail") String toEmail, @Param("fromEmail") String fromEmail, @Param("uuid") String uuid);

    List<NoteVO> getNotes(@Param("toEmail") String toEmail);

    List<NoteVO> getAllNotes(@Param("uuid") String uuid, @Param("fromEmail") String fromEmail);

    int updateLastCheckTime(@Param("fromEmail") String toEmail,@Param("uuid") String uuid);
}
