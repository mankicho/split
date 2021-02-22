package component.note;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteVO getLastNode(String toEmail, String fromEmail) {
        return noteMapper.getLastNode(toEmail, fromEmail);
    }

    // 하나의 쪽지방 쪽지들을 다 가져오기
    public List<NoteVO> getNotesByUUID(@Param("uuid") String uuid) {
        return noteMapper.getNotesByUUID(uuid);
    }

    // 모든 쪽지방 종류별로 최근 쪽지 한개씩 가져오기
    public List<NoteVO> getNoteBoxesByToEmail(@Param("toEmail") String toEmail) {
        return noteMapper.getNoteBoxesByToEmail(toEmail);
    }
}
