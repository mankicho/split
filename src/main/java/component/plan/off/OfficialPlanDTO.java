package component.plan.off;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sun.org.apache.bcel.internal.generic.DUP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.DateUtil;

import java.sql.Date;
import java.sql.Time;

@Data
public class OfficialPlanDTO {
    private String memberEmail;
    private int planId;
    private Date startDate;
    private Date endDate;
    private Time authTime;
}
