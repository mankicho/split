package component.plan.auth;

import component.plan.nonoff.NonOfficialPlanMapper;
import component.plan.off.OfficialPlanMapper;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import security.token.TokenGeneratorService;

import java.util.HashMap;

@Repository
public class PlanAuthDAO {
    @Setter(onMethod_ = {@Autowired})
    private PlanAuthMapper mapper;

    @Setter(onMethod_ = {@Autowired})
    private NonOfficialPlanMapper nonOfficialPlanMapper;

    @Setter(onMethod_ = {@Autowired})
    private OfficialPlanMapper officialPlanMapper;

    @Setter(onMethod_ = {@Autowired})
    private TokenGeneratorService tokenGeneratorService;

    public int planAuth(HashMap<String, Object> hashMap) {
        // todo 1. 플랜이 존재 하는가?
        int existPlan;
        int planType = (int) hashMap.get("type");
        if (planType == 0) { // 공식 챌린지
            existPlan = officialPlanMapper.selectByIdAndEmail(hashMap);
        } else { // 비공식 챌린지
            existPlan = nonOfficialPlanMapper.selectByIdAndEmail(hashMap);
        }

        if (existPlan == 0) { // 없는 플랜이면
            return -100;
        }

        // todo 2. 요청 데이터가 적절한가? (parameter, timestamp 등등)

        // todo 3. 인증이 가능한 시간대인가?
        // todo 4. 인증 내역 Table 에 insert
        // todo 5. todo 1~4 가 문제없이 진행되면 결과 return
        return mapper.planAuth(hashMap);
    }
}
