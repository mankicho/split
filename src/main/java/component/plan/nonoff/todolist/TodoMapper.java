package component.plan.nonoff.todolist;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface TodoMapper {
    List<TodoDTO> getTodoList(@Param("email") String email, @Param("date") String date);

    int insertTodo(TodoDTO todoDTO);

    int deleteTodo(HashMap<String,Object> hashMap);
}
