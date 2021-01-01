import component.plan.off.OfficialPlanDTO;
import component.plan.off.OfficialPlanMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring/security/security-context.xml",
        "file:src/main/webapp/WEB-INF/spring/mail/mail-context.xml"
})
@Log4j
public class OfficialPlanTest {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    @Test
    public void getTest() throws Exception {
        URL url = new URL("http://localhost:8084/plan/get.do");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("member-token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ6a3NwZmZoQG5hdmVyLmNvbSIsImV4cCI6MTYwODgyNDE1OX0.1xVQjtEXR0K7I4Si30lsc9byCY5HhEcncLiGRpWrbxA");
        connection.setRequestProperty("member-email", "zkspffh@naver.com");

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

    }

    @Test
    public void insertTest() {
        try {
            OfficialPlanDTO dto = new OfficialPlanDTO("sexy@naver.com", 2,
                    "2020-12-20", "2020-12-25", "08:30:00");

            log.info("dto = " + dto);
            URL url = new URL("http://localhost:8084/plan/insert.do");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");


            JSONObject object = new JSONObject();
            object.put("memberEmail", dto.getMemberEmail());
            object.put("authTime", "08:30:00");
            object.put("startDate", "2020-12-20");
            object.put("endDate", "2020-12-25");
            object.put("planId", 1);

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
    public void deleteTest() throws Exception {
        OfficialPlanMapper mapper = sqlSession.getMapper(OfficialPlanMapper.class);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", "sexy@naver.com");
        hashMap.put("sDate", "2020-12-25");
        hashMap.put("authtime", "08:30:00");


        int delete = mapper.deleteOfficialPlan(hashMap);
        log.info(delete);
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
