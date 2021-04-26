package component.chat;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChattingMessage {
    private String memberEmail;
    private String schoolName;
    private int schoolId;
    private int classId;
    private String className;
    private String nickname;
    private String profileImage;
    private int messageType;
    private String message;
    private String messageImagePath;
    private long transferTime;


}
