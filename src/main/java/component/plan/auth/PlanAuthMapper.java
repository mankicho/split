package component.plan.auth;

import org.apache.ibatis.annotations.Param;

public interface PlanAuthMapper {
    int planAuth(@Param("planId") int planId, @Param("planType") int planType);
}
