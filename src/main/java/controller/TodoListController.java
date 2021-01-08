package controller;

import component.plan.nonoff.todolist.TodoDTO;
import component.plan.nonoff.todolist.TodoService;
import component.plan.nonoff.todolist.TodoVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * for user's todo list
 */
@RestController
@RequestMapping(value = "/split/todo")
public class TodoListController {

    @Setter(onMethod_ = {@Autowired})
    private TodoService todoService;

    @PostMapping(value = "/get.do")
    public List<TodoVO> getTodoList(HttpServletRequest request) {
        String email = request.getParameter("email");
        String date = request.getParameter("date");
        return todoService.getTodoList(email, date);
    }

    @PostMapping(value = "/write.do")
    public int writeTodo(@RequestBody TodoDTO todoDTO) {
        return todoService.insertTodo(todoDTO);
    }

    @PostMapping(value = "delete.do")
    public int deleteTodo(HttpServletRequest request) {
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        int todoId = Integer.parseInt(id);
        TodoVO todo = todoService.selectById(todoId);

        if (todo.getMemberEmail().equals(email)) {
            int deletedRow = todoService.deleteTodo(todoId);
            if (deletedRow == 0) {
                return 500;
            }
            return 202;
        } else {
            return 400;
        }
    }
}
