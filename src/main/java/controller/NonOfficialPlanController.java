package controller;

import component.plan.nonoff.NonOfficialPlanDTO;
import component.plan.nonoff.NonOfficialPlanService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * free plan (non-official-plan)
 */
@RestController
@RequestMapping(value = "/split/nonplan")
@Log4j
public class NonOfficialPlanController {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    @Setter(onMethod_ = {@Autowired})
    private NonOfficialPlanService nonOfficialPlanService;

    @GetMapping(value = "/test.do")
    public String test() {
        log.info("test");
        return "success";
    }

    @RequestMapping(value = "/reserve.do")
    public int insertNonOfficialPlan(@RequestBody NonOfficialPlanDTO nonOfficialPlanDTO) {

        int value = nonOfficialPlanService.insertNonOfficialPlan(nonOfficialPlanDTO);
        if (value == 0) {
            return 202;
        }

        return 200;
    }
}
