package component.plan.off;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sun.org.apache.bcel.internal.generic.DUP;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.DateUtil;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
public class OfficialPlanDTO {
    private int planLogId;
    private String memberEmail;
    private int planId;
    private Date startDate;
    private Date endDate;
    private Time authTime;
    private Date regDate;

    public OfficialPlanDTO(String memberEmail, int planId, String startDate, String endDate, String authTime) {
        this.memberEmail = memberEmail;
        this.planId = planId;
        this.startDate = DateUtil.toSqlDate(startDate);
        this.endDate = DateUtil.toSqlDate(endDate);
        this.authTime = DateUtil.toSqlTime(authTime);
    }
}
