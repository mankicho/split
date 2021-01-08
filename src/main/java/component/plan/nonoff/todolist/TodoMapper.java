package component.plan.nonoff.todolist;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface TodoMapper {
    List<TodoVO> getTodoList(@Param("email") String email, @Param("date") String date);

    int insertTodo(TodoDTO todoDTO);

    int deleteTodo(@Param("todo_id") int todoId);

    TodoVO selectById(@Param("todo_id") int todoId);

}
