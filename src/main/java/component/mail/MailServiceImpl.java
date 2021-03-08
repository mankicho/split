package component.mail;

import component.member.MemberService;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@Log4j2
public class MailServiceImpl implements MailService {

    @Setter(onMethod_ = {@Autowired})
    private JavaMailSender javaMailSender;

    @Setter(onMethod_ = {@Autowired})
    private MemberService memberService;

    @Override
    public boolean sendToMemberToFindPassword(String subject, String text, String from, String to) {
        log.info("start");
        // todo 1. email 이 존재하는지
        String recordedEmail = memberService.isExistEmail(to);
        if (!to.equals(recordedEmail)) { // 등록되어있지 않은 이메일이면
            return false;
        }

        // todo 2. 메일 전송하기
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject(subject);
            helper.setText(text);
            helper.setFrom("split@studyplanet.kr");
            helper.setTo(to);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        log.info("finish");
        return true;
    }

}
