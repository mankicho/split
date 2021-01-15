package component.plan;

import lombok.Data;
@Data
public class PlanDTO {
    private String memberEmail;
    private String planName;
    private String startDate;
    private String endDate;
    private String authTime;
    private String checkoutTime;
    private String compCondition;
    private boolean pub;
    private String placeSetting;
    private String guildName;
    private String tag;
}
