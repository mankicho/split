package controller;

import component.plan.PlanAttendanceDTO;
import component.plan.PlanDTO;
import component.plan.PlanService;
import component.plan.PlanVO;
import lombok.Setter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/split/plan")
public class PlanController {

    @Setter(onMethod_ = {@Autowired})
    private PlanService planService;

    @ExceptionHandler(ParseException.class)
    public HashMap<String, Object> handlerParseException(ParseException e) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", 500);
        hashMap.put("msg", e.getMessage());
        return hashMap;
    }

    /**
     * @param planDTO 플랜 예약
     * @return
     */
    @PostMapping(value = "/insert.do")
    public HashMap<String, Object> insertPlan(@RequestBody PlanDTO planDTO) throws ParseException {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (planDTO.getPCode() == null) {
            planDTO.setPCode("");
        }
        int status = planService.insertPlan(planDTO);
        hashMap.put("status", status);
        return hashMap;
    }

    @PostMapping(value = "test/insert.do")
    public int testInsertPlan(@RequestBody PlanDTO planDTO) throws ParseException {
        if (planDTO.getPCode() == null) {
            planDTO.setPCode("");
        }
        return planService.testInsertPlan(planDTO);
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

    @PostMapping(value = "/all/emails/of/plans/select.do")
    public List<String> selectsAllEmailOfPlans(@RequestParam("planLogId") int planLogId) {
        return planService.selectsAllEmailOfPlans(planLogId);
    }

    @GetMapping(value = "/get/plans/by/search")
    public List<PlanVO> getPlansBySearching(@RequestParam("key") String key) {
        return planService.getPlansBySearching(key);
    }

}
