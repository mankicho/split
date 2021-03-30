package controller;

import component.home.HomeDataDTO;
import component.home.HomeDataService;
import component.home.view.HomeData;
import component.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * home data(today users using application, by 30minutes users, by 30minutes successful authenticating users)
 */
@Controller
@RequestMapping(value = "/")
@Log4j2
@RequiredArgsConstructor
public class HomeController {

    private final HomeDataService homeDataService;

    private final MemberService memberService;

    @GetMapping(value = "/")
    public String home() {
        return "index";
    }

    @GetMapping(value = "/test.do")
    @ResponseBody
    public Map<String, Object> test2() {
        Map<String, Object> map = new HashMap<>();
        map.put("date", new Date());
        map.put("milli", new Date().getTime());

        return map;
    }

    @PostMapping(value = "/home/data/get.do")
    @ResponseBody
    public HomeData getHomeData(@RequestBody HomeDataDTO homeDataDTO) {
        return homeDataService.getHomeData(homeDataDTO);
    }

    private int getSquareOfTwo(String val) {
        int v = Integer.parseInt(val);
        return (int) Math.pow(2, v - 1);
    }
}
