package component.plan;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class PlanVO {
    private int planLogId;
    private String memberEmail;
    private String planName;
    private Date startDate;
    private Date endDate;
    private Date authTime;
    private Date checkoutTime;
    private String compCondition;
    private boolean pub;
    private String placeSetting;
    private String guildName;
    private String tag;
    private String pCode;
}
