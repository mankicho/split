package component.plan.nonoff.todolist;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface TodoService {
    List<TodoVO> getTodoList(String email, String date);

    int insertTodo(TodoDTO todoDTO);

    int deleteTodo(int todoId);

    TodoVO selectById(int todoId);
}
