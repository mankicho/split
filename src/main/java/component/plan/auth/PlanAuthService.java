package component.plan.auth;

import org.apache.ibatis.annotations.Param;

public interface PlanAuthService {
    int planAuth(int planId, int planType);
}
