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
    private TodoMapper mapper;

    public List<TodoDTO> getTodoList(String email, String date) {
        return mapper.getTodoList(email, date);
    }

    public int insertTodo(TodoDTO todoDTO) {
        return mapper.insertTodo(todoDTO);
    }

    public int deleteTodo(HashMap<String, Object> hashMap) {
        return mapper.deleteTodo(hashMap);
    }
}
