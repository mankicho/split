package component.alarm;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmServiceImpl implements AlarmService {

    @Setter(onMethod_ = {@Autowired})
    private AlarmServiceDAO alarmServiceDAO;

    @Override
    public int saveFriendAddRequest(String fromEmail, String toEmail) {
        // 친구추가 요청 저장

        return 0;
    }
}
