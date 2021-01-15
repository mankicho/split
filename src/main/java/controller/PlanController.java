package controller;

import component.plan.PlanDTO;
import component.plan.PlanService;
import component.plan.PlanVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/split/plan")
public class PlanController {

    @Setter(onMethod_ = {@Autowired})
    private PlanService planService;

    @PostMapping(value = "/insert.do")
    public int insertPlan(@RequestBody PlanDTO planDTO) {
        return planService.insertPlan(planDTO);
    }

    @PostMapping(value = "/multi/insert.do")
    public int insertMultiPlan(@RequestBody List<PlanDTO> planDTOS) {
        return planService.insertRangePlan(planDTOS);
    }

    @PostMapping(value = "/delete.do")
    public int deletePlan(HttpServletRequest request) {
        String email = request.getParameter("email");
        String planLogId = request.getParameter("planLogId");

        int logId = Integer.parseInt(planLogId);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("planLogId", logId);
        PlanVO planVO = planService.selectByIdAndEmail(hashMap);

        if (planVO.getPlanLogId() == logId) {
            return planService.deletePlan(logId);
        }
        return -1;
    }

    @PostMapping(value = "/range/delete.do")
    public int deleteRangePlan(@RequestBody HashMap<String, String> requestBody) {
        return planService.deleteRangePlan(requestBody);
    }

    @PostMapping(value = "/select.do")
    public List<PlanVO> selectPlans(@RequestParam("email") String email) {
        return planService.selectsAllPlans(email);
    }

}
