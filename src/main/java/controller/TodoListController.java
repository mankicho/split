package controller;

import component.plan.nonoff.todolist.TodoDTO;
import component.plan.nonoff.todolist.TodoService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/todo")
@Log4j
public class TodoListController {

    @Setter(onMethod_ = {@Autowired})
    private TodoService todoService;

    @RequestMapping(value = "/todoList/get.do")
    public List<TodoDTO> getTodoList(HttpServletRequest request) {
        String email = request.getParameter("email");
        String date = request.getParameter("date");
        log.info(email+" "+date);
        return todoService.getTodoList(email, date);
    }
}
