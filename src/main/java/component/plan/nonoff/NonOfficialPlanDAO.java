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
    public List<NonOfficialPlanDTO> getNonPlan(HashMap<String, Object> hashMap) {
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
    public NonOfficialPlanDTO nonOfficialPlanShare(int nonOfficialPlanLogId) {
        return mapper.nonOfficialPlanShare(nonOfficialPlanLogId);
    }

    /**
     * @param nonId
     * @param email
     * select non plan by non_plan_id(primary key), user_email
     * @return
     */
    public NonOfficialPlanDTO selectByIdAndEmail(int nonId, String email) {
        return mapper.selectByIdAndEmail(nonId, email);
    }

}
