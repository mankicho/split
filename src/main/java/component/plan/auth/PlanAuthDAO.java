package component.plan.auth;

import component.plan.PlanMapper;
import component.plan.PlanVO;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import security.token.TokenGeneratorService;

import java.util.Date;
import java.util.HashMap;

@Repository
public class PlanAuthDAO {
    @Setter(onMethod_ = {@Autowired})
    private PlanAuthMapper planAuthMapper;

    @Setter(onMethod_ = {@Autowired})
    private PlanMapper planMapper;

    @Setter(onMethod_ = {@Autowired})
    private TokenGeneratorService tokenGeneratorService;

    public int planAuth(HashMap<String, Object> hashMap) {
        // todo 1. 플랜이 존재 하는가?
        PlanVO planVO = planMapper.selectByIdAndEmail(hashMap);
        if (planVO == null) {
            return 500;
        }

        // todo 2. 요청 데이터가 적절한가? (parameter, timestamp 등등)
        if (tokenGeneratorService.getExpiration((String) hashMap.get("token")).before(new Date())) {
            return 400;
        }
        // todo 3. 인증 내역 Table 에 insert, 결과 리턴
        return planAuthMapper.planAuth(hashMap);
    }
}