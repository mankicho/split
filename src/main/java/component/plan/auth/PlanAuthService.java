package component.plan.auth;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

public interface PlanAuthService {
    int planAuth(HashMap<String,Object> hashMap);

}
