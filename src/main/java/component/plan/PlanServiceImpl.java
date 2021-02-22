package component.plan;

import component.member.vo.MemberDeviceVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Setter(onMethod_ = {@Autowired})
    private PlanDAO planDAO;

    @Override
    public int insertPlan(PlanDTO planDTO) throws ParseException {
        return planDAO.insertPlan(planDTO);
    }

    @Override
    public int testInsertPlan(PlanDTO planDTO) throws ParseException {
        return planDAO.testInsertPlan(planDTO);
    }

    @Override
    public int deletePlan(int planLogId) {
        return planDAO.deleteOfficialPlan(planLogId);
    }

    @Override
    public PlanVO selectByIdAndEmail(HashMap<String, Object> hashMap) {
        return planDAO.selectByIdAndEmail(hashMap);
    }

    @Override
    public List<PlanVO> selectsAllPlans(String email) {
        return planDAO.selectsAllPlans(email);
    }

    @Override
    public int deleteRangePlan(HashMap<String, String> requestBody) {
        return planDAO.deleteRangePlan(requestBody);
    }

    @Override
    public int insertRangePlan(List<PlanDTO> planDTOS) {
        return planDAO.insertRangePlan(planDTOS);
    }

    @Override
    public List<String> getAllEmailOfPlans(int planLogId) {
        return planDAO.getAllEmailOfPlans(planLogId);
    }

    @Override
    public PlanVO selectTodayPlan(HashMap<String, Object> hashMap) {
        return planDAO.selectTodayPlan(hashMap);
    }

    @Override
    public List<PlanVO> selectsAllPlansAtCertainZone(String placeSetting) {
        return planDAO.selectsAllPlansAtCertainZone(placeSetting);
    }

    @Override
    public List<PlanVO> getPlansBySearching(String key) {
        return planDAO.getPlansBySearching(key);
    }

    @Override
    public List<PlanVO> getPlansByOrdering(HashMap<String, Object> hashMap) {
        return planDAO.getPlansByOrdering(hashMap);
    }

    @Override
    public List<MemberDeviceVO> getDevicesForPushNotificationOfAttendance(int weekday) {
        return planDAO.getDevicesForPushNotificationOfAttendance(weekday);
    }
}
