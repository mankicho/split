package component.plan.off;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OfficialPlanDAO {
    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    public List<OfficialPlanDTO> selects() {
        OfficialPlanMapper mapper = sqlSession.getMapper(OfficialPlanMapper.class);
        return mapper.selects();
    }
}
