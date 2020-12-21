package component.plan.nonoff.todolist;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class TodoDTO {
    private int todoId;
    private String memberEmail;
    private Date todoDate;
    private Time todoTime;
    private String todoDetail;
}
