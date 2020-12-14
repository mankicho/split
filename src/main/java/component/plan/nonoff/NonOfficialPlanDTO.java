package component.plan.nonoff;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NonOfficialPlanDTO {
  private int memberId;
  private String planName;
  private String startDate;
  private String endDate;
  private String authTime;
  private String regTime;
}
