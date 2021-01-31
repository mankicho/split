package component.alarm;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AlarmServiceDAO {
    @Setter(onMethod_ = {@Autowired})
    private AlarmMapper alarmMapper;
}
