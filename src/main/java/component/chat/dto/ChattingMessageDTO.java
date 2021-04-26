package component.chat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class ChattingMessageDTO {
    private int schoolId;
    private int classId;
    private String memberEmail; // 전송한사람
    private int mType; // 메세지유형, 사진? 문자열?
    private String message; // 메세지
}
