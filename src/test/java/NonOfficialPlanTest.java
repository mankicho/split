import component.member.MemberMapper;
import component.plan.nonoff.NonOfficialPlanDTO;
import component.plan.nonoff.NonOfficialPlanMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring/security/security-context.xml",
        "file:src/main/webapp/WEB-INF/spring/mail/mail-context.xml"
})
public class NonOfficialPlanTest {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    @Test
    public void InsertNonOfficialPlan() throws Exception {
        NonOfficialPlanDTO dto = new NonOfficialPlanDTO("zkspffh@naver.com", "토익 계획",
                "2020-12-20", "2020-12-25", "08:30:00");
//        System.out.println(connection2.getHeaderField("Set-Cookie").split(";")[0].split("=")[1]);

        URL url = new URL("http://localhost:8084/nonplan/reserve.do");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");

        JSONObject object = new JSONObject();
        object.put("memberEmail", dto.getMemberEmail());
        object.put("planName", dto.getPlanName());
        object.put("startDate", dto.getStartDate());
        object.put("endDate", dto.getEndDate());
        object.put("authTime", dto.getAuthTime());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        bw.write(object.toString());
        bw.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

    }

    @Test
    public void getNonPlan() throws Exception {
        String email = "sexy@naver.com";
        String sDate = "2020-12-20";
        NonOfficialPlanMapper mapper = sqlSession.getMapper(NonOfficialPlanMapper.class);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("sDate", sDate);

        List<NonOfficialPlanDTO> dtoList = mapper.getNonPlan(hashMap);
        log.info(dtoList);
    }

    @Test
    public void deleteNonOfficialPlanTest() throws Exception {
        NonOfficialPlanMapper mapper = sqlSession.getMapper(NonOfficialPlanMapper.class);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", "sexy@naver.com");
        hashMap.put("sDate", toSqlDate("2020-12-20"));
        hashMap.put("aTime", toSqlTime("08:00:30"));

        int affectedRow = mapper.deleteNonOfficialPlan(hashMap);

        log.info(affectedRow);
    }

    @Test
    public void nonOfficialPlanShareTest() throws Exception {
        NonOfficialPlanMapper mapper = sqlSession.getMapper(NonOfficialPlanMapper.class);

    }

    private Date toSqlDate(String str) throws Exception {
        java.util.Date date = dateFormat.parse(str);
        return new Date(date.getTime());
    }

    private Time toSqlTime(String str) throws Exception {
        java.util.Date date = timeFormat.parse(str);
        return new Time(date.getTime());
    }
}
