package controller;

import component.home.HomeDataService;
import component.member.MemberService;
import component.member.vo.MemberTimerVO;
import component.plan.PlanService;
import component.plan.PlanVO;
import component.plan.auth.PlanAuthService;
import component.plan.auth.TodayPlanAuthVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * home data(today users using application, by 30minutes users, by 30minutes successful authenticating users)
 */
@Controller
@RequestMapping(value = "/")
@Log4j2
public class HomeController {

    @Setter(onMethod_ = {@Autowired})
    private HomeDataService homeDataService;

    @Setter(onMethod_ = {@Autowired})
    private MemberService memberService;

    @Setter(onMethod_ = {@Autowired})
    private PlanService planService;

    @Setter(onMethod_ = {@Autowired})
    private PlanAuthService planAuthService;


    @ExceptionHandler({ParseException.class})
    @GetMapping(value = "/")
    public String home() {
        return "index";
    }

    @ResponseBody
    @GetMapping(value = "/home/today/allUser/get.do")
    public int selectPlanAuthLogsOfToday() {
        return homeDataService.selectPlanAuthLogsOfToday();
    }

    @ResponseBody
    @GetMapping(value = "/home/today/allUsers/by30M/get.do")
    public int selectPlanAuthLogsFor30Minutes() {
        return homeDataService.selectPlanAuthLogsFor30Minutes(new HashMap<>());
    }

    @GetMapping(value = "/home/today/success/allUsers/by30M/get.do")
    public int selectPlanAUthLogsFor30MinutesOfSuccessUsers() {
        return homeDataService.selectPlanAuthLogsFor30MinutesOfSuccessUsers("", "");
    }

    @PostMapping(value = "/home/data/get.do")
    @ResponseBody
    public HashMap<String, Object> selectHomeData(HttpServletRequest request) {
        HashMap<String, Object> hashMap = new HashMap<>();
        HashMap<String, Object> requestHashMap = new HashMap<>();
        String email = request.getParameter("email");
        String setDay = request.getParameter("setDay"); // 오늘 요일숫자
        requestHashMap.put("email", email);
        requestHashMap.put("setDay", getSquareOfTwo(setDay)); // parseException 가능성
        int allUsers = homeDataService.selectPlanAuthLogsOfToday(); // 오늘 출석체크한 유저의 총 숫자
        hashMap.put("allUsers", allUsers);
        List<MemberTimerVO> timers = memberService.selectTimer(email);
        hashMap.put("timers", timers);
        PlanVO planVO = planService.selectTodayPlan(requestHashMap);
        hashMap.put("planVO", planVO);
        if (planVO != null) {
            TodayPlanAuthVO todayPlanAuthVO = planAuthService.getAuthNumberOfMyPlan(planVO.getPlanLogId());
            hashMap.put("all", todayPlanAuthVO.getAllUser()); // 내 플랜의 총 인원수
            hashMap.put("do", todayPlanAuthVO.getDoUser()); // 내플랜에서 인증한 유저의 총 수
        }
        return hashMap;
    }

    @GetMapping("/test/test/test")
    public void test(HttpServletRequest request) throws IOException {
        System.out.println(System.getProperty("catalina.home"));

        String path = System.getProperty("catalina.home");
        FileInputStream fis = new FileInputStream(new File(path + "/test.txt"));

        int c;

        while ((c = fis.read()) != -1) {
            System.out.print((char) c);
        }
    }

    private int getSquareOfTwo(String val) {
        int v = Integer.parseInt(val);
        return (int) Math.pow(2, v - 1);
    }
}
