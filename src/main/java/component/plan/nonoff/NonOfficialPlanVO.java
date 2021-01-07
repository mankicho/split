package component.plan.nonoff;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

/**
 * just select
 */
@Data
public class NonOfficialPlanVO {
    private int nonOfficialPlanId;
    private String memberEmail;
    private String planName;
    private Date startDate;
    private Date endDate;
    private Time authTime;
    private Date regTime;
}
