package controller;

import component.plan.off.OfficialPlanDTO;
import component.plan.off.OfficialPlanService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/plan")
public class OfficialPlanController {

    @Setter(onMethod_ = {@Autowired})
    private OfficialPlanService officialPlanService;

    @GetMapping(value = "/get.do")
    public List<OfficialPlanDTO> getPlans() {
        return officialPlanService.selects();
    }
}
