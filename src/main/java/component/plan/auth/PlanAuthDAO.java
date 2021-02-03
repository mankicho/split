package component.plan.auth;

import component.plan.PlanMapper;
import component.plan.PlanVO;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import security.token.TokenGeneratorService;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class PlanAuthDAO {
    @Setter(onMethod_ = {@Autowired})
    private PlanAuthMapper planAuthMapper;

    @Setter(onMethod_ = {@Autowired})
    private PlanMapper planMapper;

    @Setter(onMethod_ = {@Autowired})
    private TokenGeneratorService tokenGeneratorService;

    private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");

    public int attendant(HashMap<String, Object> hashMap) {
        String token = (String) hashMap.get("token");
        // todo 1. 토큰검사(만료기간, 데이터)
        if (tokenGeneratorService.getExpiration(token).before(new Date())) {
            System.out.println(tokenGeneratorService.getExpiration(token));
            System.out.println(new Date());
            return 400; // 부적절한 데이터
        }
        // todo 2. 인증해야 할 플랜이 존재 하는가?
        List<PlanVO> planVOList = planMapper.selectByEmailAndDate(hashMap);
        if (planVOList.isEmpty()) {
            return 500; // 오늘은 인증할 플랜이 없다.
        }
        System.out.println(planVOList);
        // todo 3. 요청 데이터가 적절한가? (parameter, timestamp 등등)
        int weekday = squareOfTwo((int) hashMap.get("weekday"));
        int andResult = 0;
        int planLogId = 0;
        for (int i = 0; i < planVOList.size(); i++) {
            int setDay = planVOList.get(i).getSetDay();
            andResult = weekday & setDay;
            if (andResult == 0) {
                continue;
            }
            planLogId = planVOList.get(i).getPlanLogId();
            break;
            // 현재시간과 설정시간을 비교
            // 한시간 이내면 인증 성공

        }
        if (andResult == 0) {
            return 401; // 오늘은 인증하는 날이 아님.
        }

        hashMap.put("planLogId", planLogId);
        // todo 4. 인증 내역 Table 에 insert, 결과 리턴
        return planAuthMapper.planAuthLog(hashMap);
    }

    private int squareOfTwo(int weekday) {
        return (int) Math.pow(2, weekday - 1);
    }

}