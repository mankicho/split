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
    private String authTime;
    private String compCondition;
    private int setDay;
    private boolean pub;
    private String placeSetting;
    private String guildName;
    private String tag;
    private String pCode;
}
