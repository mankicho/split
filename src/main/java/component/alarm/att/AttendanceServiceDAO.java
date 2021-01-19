package component.alarm.att;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttendanceServiceDAO {
    @Setter(onMethod_ = {@Autowired})
    private AttendanceMapper attendanceMapper;
}
