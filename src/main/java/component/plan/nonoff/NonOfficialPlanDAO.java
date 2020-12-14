package component.plan.nonoff;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NonOfficialPlanDAO {

    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    public int insertNonOfficialPlan(NonOfficialPlanDTO nonOfficialPlanDTO) {
        return sqlSession.insert("component.plan.nonoff.NonOfficialPlanMapper.insertNonOfficialPlan", nonOfficialPlanDTO);
    }

}
