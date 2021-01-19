package component.alarm.fri;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FriendAddRequestAlarmDAO {
    @Setter(onMethod_ = {@Autowired})
    private FriendAddRequestAlarmMapper friendAddRequestAlarmMapper;
}
