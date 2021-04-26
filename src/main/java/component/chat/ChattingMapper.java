package component.chat;

import component.chat.dto.ChattingMessageDTO;
import component.chat.dto.GetChattingDTO;

import java.util.List;

public interface ChattingMapper {
    ChattingMessageDTO send(ChattingMessageDTO dto);

    List<ChattingMessageDTO> getAllMessages(GetChattingDTO getChattingDTO);
}
