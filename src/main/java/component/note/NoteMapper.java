package component.note;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoteMapper {
    int saveNote(NoteDTO noteDTO);

    NoteVO getLastNode(@Param("toEmail") String toEmail, @Param("fromEmail") String from); //

    List<NoteVO> getNotesByUUID(@Param("uuid") String uuid); // 하나의 쪽지방 쪽지들을 다 가져오기

    List<NoteVO> getNoteBoxesByToEmail(@Param("toEmail") String toEmail); // 모든 쪽지방 종류별로 최근 쪽지 한개씩 가져오기

}
