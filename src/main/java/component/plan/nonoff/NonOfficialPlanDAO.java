package component.plan.nonoff;

import lombok.Setter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class NonOfficialPlanDAO {

    @Setter(onMethod_ = {@Autowired})
    private NonOfficialPlanMapper mapper;

    /**
     * @param nonOfficialPlanDTO non plan reserve
     * @return
     */
    public int insertNonOfficialPlan(NonOfficialPlanDTO nonOfficialPlanDTO) {
        return mapper.insertNonOfficialPlan(nonOfficialPlanDTO);
    }

    /**
     * @param hashMap bring all non plan of user
     * @return
     */
    public List<NonOfficialPlanVO> getNonPlan(HashMap<String, Object> hashMap) {
        return mapper.getNonPlan(hashMap);
    }

    /**
     * @param hashMap delete user's non plan
     * @return
     */
    public int deleteNonOfficialPlan(HashMap<String, Object> hashMap) {
        return mapper.deleteNonOfficialPlan(hashMap);
    }

    /**
     * @param nonOfficialPlanLogId bring certain user's non plan
     *                             example
     *                             user A brings user B's non plan(title,content,period)
     * @return
     */
    public NonOfficialPlanVO nonOfficialPlanShare(String nonOfficialPlanLogId) {
        return mapper.nonOfficialPlanShare(nonOfficialPlanLogId);
    }

    /**
     * @param hashMap select non plan by non_plan_id(primary key), user_email
     * @return
     */
    public Integer selectByIdAndEmail(HashMap<String, Object> hashMap) {
        return mapper.selectByIdAndEmail(hashMap);
    }

}
