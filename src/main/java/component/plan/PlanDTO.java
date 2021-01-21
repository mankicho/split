package component.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {
    private String memberEmail;
    private String planName;
    private String startDate;
    private String endDate;
    private String authTime;
    private String compCondition;
    private int setDay;
    private boolean pub;
    private String placeSetting;
    private String guildName;
    private String tag;
    private String pCode;
}
