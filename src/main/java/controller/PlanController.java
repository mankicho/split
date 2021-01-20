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

    /**
     * @param planDTO 플랜 예약
     * @return
     */
    @PostMapping(value = "/insert.do")
    public int insertPlan(@RequestBody PlanDTO planDTO) {
        System.out.println(planDTO);
        return planService.insertPlan(planDTO);
    }

    /**
     * @param planDTOS 플랜 여러개 예약
     * @return
     */
    @PostMapping(value = "/multi/insert.do")
    public int insertMultiPlan(@RequestBody List<PlanDTO> planDTOS) {
        return planService.insertRangePlan(planDTOS);
    }

    /**
     * @param request 플랜 삭제하기
     * @return
     */
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

    /**
     * @param requestBody 플랜 여러개 삭제하기
     */
    @PostMapping(value = "/range/delete.do")
    public int deleteRangePlan(@RequestBody HashMap<String, String> requestBody) {
        return planService.deleteRangePlan(requestBody);
    }

    /**
     * @param email 유저의 모든 플랜 가져오기
     * @return
     */
    @PostMapping(value = "/select.do")
    public List<PlanVO> selectPlans(@RequestParam("email") String email) {
        return planService.selectsAllPlans(email);
    }

}
