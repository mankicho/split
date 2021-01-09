package component.plan.off;

import lombok.Setter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class OfficialPlanDAO {

    @Setter(onMethod_ = {@Autowired})
    private OfficialPlanMapper mapper;

    public List<OfficialPlanVO> selects() {
        return mapper.selects();
    }

    public int insertOfficialPlan(OfficialPlanDTO officialPlanDTO) {
        return mapper.insertOfficialPlan(officialPlanDTO);
    }

    public int deleteOfficialPlan(HashMap<String, Object> hashMap) {
        return mapper.deleteOfficialPlan(hashMap);
    }

    public int selectByIdAndEmail(HashMap<String, Object> hashMap) {
        return mapper.selectByIdAndEmail(hashMap);
    }

    public List<OfficialPlanLogVO> selectsAllPlans(String email) {
        return mapper.selectsAllPlans(email);
    }
}
