package controller;

import component.home.HomeDataService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Setter(onMethod_ = {@Autowired})
    private HomeDataService homeDataService;

    @GetMapping(value = "/")
    public String home() {
        return "index";
    }

    @GetMapping(value = "/customLogin")
    public void login() {
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
}
