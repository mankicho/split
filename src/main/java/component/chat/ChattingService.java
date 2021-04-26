package component.chat;

import component.chat.dto.ChattingMessageDTO;
import component.chat.dto.GetChattingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChattingService {

    private final ChattingMapper chattingMapper;

    public ChattingMessageDTO send(ChattingMessageDTO dto) {
        return chattingMapper.send(dto);
    }

    public List<ChattingMessageDTO> getAllMessages(GetChattingDTO getChattingDTO) {
        return chattingMapper.getAllMessages(getChattingDTO);
    }
}
