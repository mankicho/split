package component.plan;

import jdk.nashorn.internal.scripts.JO;
import lombok.Setter;
import org.apache.ibatis.annotations.Param;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class PlanDAO {
    @Setter(onMethod_ = {@Autowired})
    private PlanMapper planMapper;

    public int insertOfficialPlan(PlanDTO planDTO) {
        return planMapper.insertPlan(planDTO);
    }

    public int deleteOfficialPlan(int planLogId) {
        return planMapper.deletePlan(planLogId);
    }

    public PlanVO selectByIdAndEmail(HashMap<String, Object> hashMap) {
        return planMapper.selectByIdAndEmail(hashMap);
    }

    public List<PlanVO> selectsAllPlans(@Param("email") String email) {
        return planMapper.selectsAllPlans(email);
    }

    public int deleteRangePlan(HashMap<String, String> requestBody) {
        return planMapper.deleteRangePlan(requestBody);
    }

    public int insertRangePlan(List<PlanDTO> planDTOS) {
        int insertedRow = 0;
        for (PlanDTO dto : planDTOS) {
            System.out.println(dto);
            insertedRow += planMapper.insertPlan(dto);
        }
        return insertedRow;
    }

}