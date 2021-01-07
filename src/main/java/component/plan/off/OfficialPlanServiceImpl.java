package component.plan.off;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OfficialPlanServiceImpl implements OfficialPlanService {

    @Setter(onMethod_ = {@Autowired})
    private OfficialPlanDAO officialPlanDAO;

    @Override
    public List<OfficialPlanVO> selects() {
        return officialPlanDAO.selects();
    }

    /**
     * @param officialPlanDTO 공식플랜 예약기능
     * @return
     */
    @Override
    public int insertOfficialPlan(OfficialPlanDTO officialPlanDTO) {
        return officialPlanDAO.insertOfficialPlan(officialPlanDTO);
    }

    /**
     * @param hashMap 공식 플랜 삭제기능
     * @return
     */
    @Override
    public int deleteOfficialPlan(HashMap<String, Object> hashMap) {
        return officialPlanDAO.deleteOfficialPlan(hashMap);
    }

    /**
     * @param hashMap 플랜 로그 ID 와 이메일로 플랜을 조회(예약 시 존재하는 플랜인지 확인하기 위해)
     * @return
     */
    @Override
    public int selectByIdAndEmail(HashMap<String, Object> hashMap) {
        return officialPlanDAO.selectByIdAndEmail(hashMap);
    }

    /**
     * @param email 특정 유저의 모든 플랜을 가져오기
     * @return
     */
    @Override
    public List<OfficialPlanLogVO> selectsAllPlans(String email,String sDate) {
        return officialPlanDAO.selectsAllPlans(email,sDate);
    }
}
