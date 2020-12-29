package component.alarm;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface AlarmMessageMapper {
    List<AlarmMessageDTO> selectMessages(HashMap<String,Object> hashMap);

    int updateCheckFlag(@Param("messageId") int messageId);

    int getMaxSeq(@Param("email") String email);

    int saveMessage(AlarmMessageDTO messageDTO);
}
