package component.plan.nonoff;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class NonOfficialPlanDAO {

    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    public int insertNonOfficialPlan(NonOfficialPlanDTO nonOfficialPlanDTO) {
        NonOfficialPlanMapper mapper = sqlSession.getMapper(NonOfficialPlanMapper.class);
        return mapper.insertNonOfficialPlan(nonOfficialPlanDTO);
    }

    public List<NonOfficialPlanDTO> getNonPlan(HashMap<String, Object> hashMap) {
        NonOfficialPlanMapper mapper = sqlSession.getMapper(NonOfficialPlanMapper.class);
        return mapper.getNonPlan(hashMap);
    }

    public int deleteNonOfficialPlan(HashMap<String, Object> hashMap) {
        NonOfficialPlanMapper mapper = sqlSession.getMapper(NonOfficialPlanMapper.class);
        return mapper.deleteNonOfficialPlan(hashMap);
    }


}
