package controller;

import component.home.HomeDataService;
import component.member.MemberService;
import component.member.MemberTimerVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * home data(today users using application, by 30minutes users, by 30minutes successful authenticating users)
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Setter(onMethod_ = {@Autowired})
    private HomeDataService homeDataService;

    @Setter(onMethod_ = {@Autowired})
    private MemberService memberService;

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
        String email = request.getParameter("email");
        int allUsers = homeDataService.selectPlanAuthLogsOfToday();
        hashMap.put("users", allUsers);
        int usersTotalCheck = homeDataService.selectUsersTotalCheckTime(email);
        hashMap.put("checks", usersTotalCheck);
        List<MemberTimerVO> timers = memberService.selectTimer(email);
        hashMap.put("timers", timers);
        return hashMap;
    }

}
