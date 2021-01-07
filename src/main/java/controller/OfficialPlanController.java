package controller;

import component.plan.off.OfficialPlanDTO;
import component.plan.off.OfficialPlanService;
import component.plan.off.OfficialPlanVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

/**
 * official plan
 */
@RestController
@RequestMapping(value = "/split/plan")
public class OfficialPlanController {

    @Setter(onMethod_ = {@Autowired})
    private OfficialPlanService officialPlanService;

    @GetMapping(value = "/get.do")
    public List<OfficialPlanVO> getPlans() {
        return officialPlanService.selects();
    }

    @PostMapping(value = "/insert.do")
    public int insertOfficialPlan(@RequestBody OfficialPlanDTO officialPlanDTO) {

        return officialPlanService.insertOfficialPlan(officialPlanDTO);
    }

    @PostMapping(value = "/delete.do")
    public int deleteOfficialPlan(@RequestBody HashMap<String, Object> hashMap) {
        return officialPlanService.deleteOfficialPlan(hashMap);
    }

    @PostMapping(value = "/selectsAllPlans.do")
    public List<OfficialPlanDTO> selectsAllPlans(HttpServletRequest request) {
        String email = request.getParameter("email");
        String sDate = request.getParameter("sDate");
        return officialPlanService.selectsAllPlans(email,sDate);
    }
}

