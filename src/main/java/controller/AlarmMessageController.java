package controller;

import component.alarm.AlarmMessageDTO;
import component.alarm.AlarmMessageService;
import component.member.MemberDTO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * real-time alarm service(if user A shares user B then system tell B that A shared B's plan)
 */
@RestController
@RequestMapping(value = "/alarm")
public class AlarmMessageController {

    @Setter(onMethod_ = {@Autowired})
    private AlarmMessageService messageService;

    @PostMapping(value = "/get.do")
    public List<AlarmMessageDTO> getMessages(HttpServletRequest request) {
        String email = request.getParameter("email");
        String mSeq = request.getParameter("mSeq");

        int mSeqIntValue = Integer.parseInt(mSeq);
        return messageService.selectMessages(getHashMapFrom(email, mSeqIntValue));
    }

    @RequestMapping(value = "/update.do")
    public int updateCheckFlag(@RequestParam String messageId) {
        int msgId = Integer.parseInt(messageId);
        return messageService.updateCheckFlag(msgId);
    }

    private HashMap<String, Object> getHashMapFrom(String email, int messageSeq) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("messageSeq", messageSeq);
        return hashMap;
    }
}
