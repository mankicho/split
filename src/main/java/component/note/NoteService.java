package component.note;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteMapper noteMapper;

    public String getUUID(String toEmail, String fromEmail) { // uuid 가져오기
        return noteMapper.getUUID(toEmail, fromEmail, fromEmail, toEmail);
    }

    public int createNoteBox(String toEmail, String fromEmail, String uuid) {
        return noteMapper.createNoteBox(toEmail, fromEmail, uuid);
    }

    public List<NoteVO> getNotes(String toEmail) {
        return noteMapper.getNotes(toEmail);
    }

    public List<NoteVO> getAllNotes(String uuid, String fromEmail) {
        return noteMapper.getAllNotes(uuid, fromEmail);
    }

    public int updateLastCheckTime(String fromEmail,  String uuid) {
        return noteMapper.updateLastCheckTime(fromEmail, uuid);
    }
}
