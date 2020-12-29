package component.alarm;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface AlarmMessageService {
    List<AlarmMessageDTO> selectMessages(HashMap<String,Object> hashMap);

    int updateCheckFlag(int messageId);

    int getMaxSeq(String email);

    int saveMessage(AlarmMessageDTO messageDTO);
}
