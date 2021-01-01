import component.member.MemberDAO;
import component.member.MemberDTO;
import component.member.MemberMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring/security/security-context.xml",
        "file:src/main/webapp/WEB-INF/spring/mail/mail-context.xml"
})
@Log4j
public class MemberMapperTest {

    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    @Setter(onMethod_ = {@Autowired})
    private BCryptPasswordEncoder passwordEncoder;

    @Setter(onMethod_ = {@Autowired})
    private MemberDAO dao;

    @Test
    public void test() {
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);

        log.info("------------------");
        log.info(memberMapper);

        MemberDTO dto = memberMapper.selects("zkspffh@naver.com");
        log.info("dto = " + dto);

        String pw = passwordEncoder.encode("123456d3baca6d5d40fd92");
        log.info("pw = " + pw);
        log.info("encodedPw = " + dto.getPw());

        log.info(passwordEncoder.matches("123456d3baca6d5d40fd92", dto.getPw()));
    }

    @Test
    public void registerMemberTest() {
        MemberDTO memberDTO = new MemberDTO("asd@naver.com", "123456", "01028470440", "M", "950421", "MANKI");
        log.info("memberDTO = " + memberDTO);
//        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
//        log.info("memberMapper = " + memberMapper);
//        int affectRow = memberMapper.registerMember(memberDTO);
//        log.info("affectRow = " + affectRow);
//
        JSONObject object = new JSONObject();
        object.put("email", memberDTO.getEmail());
        object.put("pw", memberDTO.getPw());
        object.put("phoneNumber", memberDTO.getPhoneNumber());
        object.put("sex", memberDTO.getSex());
        object.put("bornTime", memberDTO.getBornTime());
        object.put("nickname", memberDTO.getNickname());
        try {
            URL url = new URL("http://localhost:8084/member/register.do");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");


            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            bufferedWriter.write(object.toString());
            bufferedWriter.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        dao.deleteMember("zkspffh@naver.com2");
    }

    @Test
    public void update() {
        System.out.println(dao.isExistNickname("asd"));
    }
}
