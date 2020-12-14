package controller;

import component.plan.nonoff.NonOfficialPlanDTO;
import component.plan.nonoff.NonOfficialPlanService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/nonplan")
@Log4j
public class NonOfficialPlanController {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    @Setter(onMethod_ = {@Autowired})
    private NonOfficialPlanService nonOfficialPlanService;

    @GetMapping(value = "/test.do")
    public String test(){
        log.info("test");
        return "success";
    }

    @PostMapping(value = "/reserve.do")
    public String insertNonOfficialPlan(HttpServletRequest request) {
        String memberId = request.getParameter("mId");
        String planName = request.getParameter("pName");
        String sDate = request.getParameter("sDate");
        String eDate = request.getParameter("eDate");
        String authT = request.getParameter("authT");
        log.info(memberId + "," + planName + "," + sDate + "," + eDate + "," + authT);
        if (memberId == null || planName == null || sDate == null || eDate == null || authT == null) {
            return "data error";
        }

        int mId;
        try {
            mId = Integer.parseInt(memberId);
            dateFormat.parse(sDate);
            dateFormat.parse(eDate);
            timeFormat.parse(authT);
        } catch (Exception e) {
            return "data parsing error";
        }
        NonOfficialPlanDTO nonOfficialPlanDTO = new NonOfficialPlanDTO(mId, planName, sDate, eDate, authT, format.format(new Date()));

        int value = nonOfficialPlanService.insertNonOfficialPlan(nonOfficialPlanDTO);
        if (value == 0) {
            return "reservation fail";
        }

        return "reservation success";
    }
}
