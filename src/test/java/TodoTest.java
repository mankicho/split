import component.plan.nonoff.todolist.TodoDAO;
import component.plan.nonoff.todolist.TodoDTO;
import component.plan.nonoff.todolist.TodoMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring/security/security-context.xml",
        "file:src/main/webapp/WEB-INF/spring/mail/mail-context.xml"
})
public class TodoTest {

    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    @Setter(onMethod_ = {@Autowired})
    private TodoDAO todoDAO;

    @Test
    public void getTodoList() {
        String email = "zkspffh@naver.com";
        String date = "2020-12-20";

        log.info(sqlSession);
        log.info(todoDAO);

        List<TodoDTO> todoDTOS1 = todoDAO.getTodoList(email,date);
        log.info(todoDTOS1);
    }
}
