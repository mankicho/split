package component.mail;

import component.member.MemberService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService {

    @Setter(onMethod_ = {@Autowired})
    private JavaMailSender javaMailSender;

    @Setter(onMethod_ = {@Autowired})
    private MemberService memberService;

    /**
     * @param email     SPLIT main email -> SPLIT user (for finding user's password)
     * @return
     */
    @Override
    public boolean sendToMemberToFindPassword(String email) {
        // todo 1. email 이 존재하는지
        String recordedEmail = memberService.isExistEmail(email);
        if (!email.equals(recordedEmail)) { // 등록되어있지 않은 이메일이면
            return false;
        }

        // todo 2. 메일 전송하기
        String subject = "[SPLIT] 비밀번호 찾기 코드입니다.";
        String randomMessage = generateSalt();
        String content = randomMessage + "\n 를 입력해주세요";
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject(subject);
            helper.setText(content);
            helper.setFrom("split@studyplanet.kr");
            helper.setTo(email);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String generateSalt() {
        Random random = new Random();

        byte[] salt = new byte[4];
        random.nextBytes(salt);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < salt.length; i++) {
            // byte 값을 Hex 값으로 바꾸기.
            sb.append(String.format("%02x", salt[i]));
        }
        return sb.toString();
    }
}
