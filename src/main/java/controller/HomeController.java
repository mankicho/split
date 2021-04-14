package controller;

import component.home.HomeDataDTO;
import component.home.HomeDataService;
import component.home.view.HomeData;
import component.home.vo.HomeDataMyInfo;
import component.home.vo.HomeExplorerVO;
import component.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
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
    public String home() throws Exception {
        log.info("user request..");
        return "index";
    }

    @PostMapping(value = "/home/data/get.do")
    @ResponseBody
    public HomeData getHomeData(@RequestBody HomeDataDTO homeDataDTO) {
        // todo 1. HomeDataMyInfo 가져오기
        HomeDataMyInfo myInfo = homeDataService.getMyHomeInfo(homeDataDTO);
        log.info(myInfo);
        // todo 2. HomeExplorerVO List 가져오기
        List<HomeExplorerVO> homeExplorerVOS = homeDataService.getMyHomeExplorers(homeDataDTO);
        log.info(homeExplorerVOS);

        return HomeData.builder()
                .memberEmail(homeDataDTO.getMemberEmail())
                .homeDataMyInfo(myInfo)
                .homeExplorerVOS(homeExplorerVOS)
                .build();

    }

    private int getSquareOfTwo(String val) {
        int v = Integer.parseInt(val);

        return (int) Math.pow(2, v - 1);
    }
}
