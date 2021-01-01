import com.google.protobuf.ByteString;
import component.home.HomeDataMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring/security/security-context.xml",
        "file:src/main/webapp/WEB-INF/spring/mail/mail-context.xml"
})
@Log4j
public class HomeTest {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    @Test
    public void homeDataTest() {
        HomeDataMapper mapper = sqlSession.getMapper(HomeDataMapper.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("fromtime", "08:00:00");
        hashMap.put("totime", "08:30:00");
        int allUsersBy30Minutes = mapper.selectPlanAuthLogsFor30Minutes(hashMap);
        int allSuccessUsersBy30Minutes = mapper.selectPlanAuthLogsFor30MinutesOfSuccessUsers("2020-12-22 08:00:00", "2020-12-22 08:30:00");
        int allUsersOfToday = mapper.selectPlanAuthLogsOfToday();

//        log.info(allUsersBy30Minutes);
        log.info(allSuccessUsersBy30Minutes + "," + allUsersOfToday + "," + allUsersBy30Minutes);
    }

    @Test
    public void homeTest() throws Exception {
        URL url = new URL("http://localhost:8084/plan/get.do");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("member-token", "123");
        connection.setRequestProperty("member-credentials", "456");

        InputStream is = connection.getInputStream();

        int c;

        while ((c = is.read()) != -1) {
            System.out.print((char) c);
        }
    }

}
