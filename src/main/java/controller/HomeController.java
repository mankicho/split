package controller;

import component.home.HomeDataService;
import component.home.view.HomeData;
import component.member.MemberService;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public HomeData getHomeData(){
        return null;
    }
    private int getSquareOfTwo(String val) {
        int v = Integer.parseInt(val);
        return (int) Math.pow(2, v - 1);
    }
}
