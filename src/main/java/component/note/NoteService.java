package component.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteMapper noteMapper;

    public List<NoteVO> getNotes(String toEmail, String fromEmail){
        return noteMapper.getNotes(toEmail,fromEmail);
    }
}
