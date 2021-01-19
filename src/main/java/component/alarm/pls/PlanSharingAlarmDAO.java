package component.alarm.pls;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlanSharingAlarmDAO {
    @Setter(onMethod_ = {@Autowired})
    private PlanSharingAlarmMapper planSharingAlarmMapper;
}
