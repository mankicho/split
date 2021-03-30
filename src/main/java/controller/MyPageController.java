package controller;

import component.mypage.MyPageMainDTO;
import component.mypage.MyPageMainVO;
import component.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/my/page")
@RequiredArgsConstructor
@RestController
@Log4j2
public class MyPageController {

    private final MyPageService myPageService;

    @PostMapping(value = "/main/get.do")
    public MyPageMainVO getMyPageMainVO(@RequestBody MyPageMainDTO myPageMainDTO) {
        return myPageService.getMyMainPageVO(myPageMainDTO);
    }
}
