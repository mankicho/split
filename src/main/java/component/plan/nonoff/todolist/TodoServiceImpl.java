package component.plan.nonoff.todolist;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Setter(onMethod_ = {@Autowired})
    private TodoDAO todoDAO;

    @Override
    public List<TodoVO> getTodoList(String email, String date) {
        return todoDAO.getTodoList(email, date);
    }

    @Override
    public int insertTodo(TodoDTO todoDTO) {
        return todoDAO.insertTodo(todoDTO);
    }

    @Override
    public int deleteTodo(int todoId) {
        return todoDAO.deleteTodo(todoId);
    }

    @Override
    public TodoVO selectById(int todoId) {
        return todoDAO.selectById(todoId);
    }
}
