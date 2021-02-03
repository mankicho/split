package component.plan.auth;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

public interface PlanAuthMapper {
    int planAuthLog(HashMap<String,Object> hashMap);

}
