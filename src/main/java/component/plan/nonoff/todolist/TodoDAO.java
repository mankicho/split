package component.plan.nonoff.todolist;

import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class TodoDAO {
    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    public List<TodoDTO> getTodoList(String email, String date) {
        TodoMapper mapper = sqlSession.getMapper(TodoMapper.class);
        return mapper.getTodoList(email, date);
    }

    public int insertTodo(TodoDTO todoDTO) {
        TodoMapper mapper = sqlSession.getMapper(TodoMapper.class);
        return mapper.insertTodo(todoDTO);
    }

    public int deleteTodo(HashMap<String, Object> hashMap) {
        TodoMapper mapper = sqlSession.getMapper(TodoMapper.class);
        return mapper.deleteTodo(hashMap);
    }
}
