package component.plan.auth;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlanAuthDAO {
    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    public int planAuth(int planId, int planType) {
        PlanAuthMapper mapper = sqlSession.getMapper(PlanAuthMapper.class);
        return mapper.planAuth(planId, planType);
    }
}
