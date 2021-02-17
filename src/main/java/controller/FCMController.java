package controller;

import fcm.FcmNotifier;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/fcm")
public class FCMController {

    @Setter(onMethod_ = {@Autowired})
    private FcmNotifier fcmNotifier;

    @GetMapping(value = "/test.do")
    public void sendMsg(HttpServletRequest request) {
        String tokenId = request.getParameter("tokenId");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        fcmNotifier.sendFCM(tokenId, title, content);
    }
}
