package component.alarm;

import lombok.Setter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class AlarmMessageDAO {

    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    public List<AlarmMessageDTO> selectMessages(HashMap<String, Object> hashMap) {
        AlarmMessageMapper messageMapper = sqlSession.getMapper(AlarmMessageMapper.class);
        return messageMapper.selectMessages(hashMap);
    }

    public int updateCheckFlag(int messageId) {
        AlarmMessageMapper messageMapper = sqlSession.getMapper(AlarmMessageMapper.class);
        return messageMapper.updateCheckFlag(messageId);
    }

    public int getMaxSeq(@Param("email") String email) {
        AlarmMessageMapper messageMapper = sqlSession.getMapper(AlarmMessageMapper.class);
        return messageMapper.getMaxSeq(email);
    }

    public int saveMessage(AlarmMessageDTO messageDTO) {
        AlarmMessageMapper messageMapper = sqlSession.getMapper(AlarmMessageMapper.class);
        return messageMapper.saveMessage(messageDTO);
    }
}
