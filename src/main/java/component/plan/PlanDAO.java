package component.plan;

import component.member.vo.MemberDeviceVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class PlanDAO {
    @Setter(onMethod_ = {@Autowired})
    private PlanMapper planMapper;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public int testInsertPlan(PlanDTO planDTO) throws ParseException {
        return planMapper.insertPlan(planDTO);
    }

    public int insertPlan(PlanDTO planDTO) throws ParseException {
        List<PlanVO> planVOS = planMapper.selectsAllPlans(planDTO.getMemberEmail());
        // todo 1. 날짜가 겹치는지 확인. parseException 의 가능성은 controller 에서 전부 filter 됨.
        Date startDate = format.parse(planDTO.getStartDate());
        Date endDate = format.parse(planDTO.getEndDate());

        final List<PlanVO> tmpDateList = new ArrayList<>(); // 겹치는 기간을 담아두기위한 임시 List
        planVOS.forEach(planVO -> { // DB 에 저장되어있는 모든 plan 을 순회
            Date startDateInDB = planVO.getStartDate();
            Date endDateInDB = planVO.getEndDate();
            if (startDate.compareTo(endDateInDB) <= 0 && endDate.compareTo(startDateInDB) >= 0) { // 기간이 겹치면
                tmpDateList.add(planVO); // 임시 리스트에 저장
            }
        });
        // todo 2. 요일이 겹치는지 확인
        for (PlanVO planVO : tmpDateList) {
            int mySetDay = planDTO.getSetDay();
            int setDayInDB = planVO.getSetDay();
            if ((mySetDay & setDayInDB) != 0) {
                // todo 3. 요일이 겹치면 겹치는 요일중에 날짜가 겹치는지 확인
                if (isSameWeekDayAndDate(planVO, planDTO)) { // 해당 플랜과 내가 신청한 플랜을 비교해서 겹치면
                    return 400;
                }
            }
        }
        int insertedRow = planMapper.insertPlan(planDTO);
        if (insertedRow == 0) {
            return 500;
        } else {
            return 202;
        }
    }

    public int deleteOfficialPlan(int planLogId) {
        return planMapper.deletePlan(planLogId);
    }

    public PlanVO selectByIdAndEmail(HashMap<String, Object> hashMap) {
        return planMapper.selectByIdAndEmail(hashMap);
    }

    public List<PlanVO> selectsAllPlans(String email) {
        return planMapper.selectsAllPlans(email);
    }

    public int deleteRangePlan(HashMap<String, String> requestBody) {
        return planMapper.deleteRangePlan(requestBody);
    }

    public int insertRangePlan(List<PlanDTO> planDTOS) {
        int insertedRow = 0;
        for (PlanDTO dto : planDTOS) {
            System.out.println(dto);
            insertedRow += planMapper.insertPlan(dto);
        }
        return insertedRow;
    }

    public List<String> getAllEmailOfPlans(int planLogId) {
        return planMapper.getAllEmailOfPlans(planLogId);
    }

    public PlanVO selectTodayPlan(HashMap<String, Object> hashMap) {
        return planMapper.selectTodayPlan(hashMap);
    }

    public List<PlanVO> selectsAllPlansAtCertainZone(String placeSetting) {
        return planMapper.selectsAllPlansAtCertainZone(placeSetting);
    }

    public List<PlanVO> getPlansBySearching(String key) {
        return planMapper.getPlansBySearching(key);
    }

    public List<PlanVO> getPlansByOrdering(HashMap<String, Object> hashMap) {
        return planMapper.getPlansByOrdering(hashMap);
    }

    public List<MemberDeviceVO> getDevicesForPushNotificationOfAttendance(int weekday) {
        return planMapper.getDevicesForPushNotificationOfAttendance(weekday);
    }

    private boolean isSameWeekDayAndDate(PlanVO planVOInDB, PlanDTO planDTO) throws ParseException {
        List<Date> dates = new ArrayList<>();
        for (Date d = planVOInDB.getStartDate(); // DB 에 저장된 시작날짜~끝날짜 사이 하루하루를 다 순회
             d.compareTo(planVOInDB.getEndDate()) <= 0;
             d.setTime(d.getTime() + 1000 * 60 * 60 * 24L)) {
            dates.add(d);
        }
        for (int i = 0; i < 7; i++) {
            if ((getSquareOfTwo(i) & planVOInDB.getSetDay() & planDTO.getSetDay()) != 0) { // 겹치는요일
                for (Date date = format.parse(planDTO.getStartDate());
                     date.compareTo(format.parse(planDTO.getEndDate())) <= 0;
                     date.setTime(date.getTime() + 1000 * 60 * 60 * 24L)) { // insert 하고자 하는 플랜의 시작~끝날짜 하루하루 다 순회
                    if (getWeekDay(date) == i) { // 겹치는 요일이 뭔지 찾아내고
                        if (dates.contains(date)) {
                            return true;
                        }
                        date.setTime(date.getTime() + 1000 * 60 * 60 * 24 * 6L);
                    }
                }
            }
        }
        return false;
    }

    private int getSquareOfTwo(int val) {
        return (int) Math.pow(2, val);
    }

    private int getWeekDay(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
}