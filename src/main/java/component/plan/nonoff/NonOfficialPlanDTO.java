package component.plan.nonoff;

import lombok.AllArgsConstructor;
import lombok.Data;
import util.DateUtil;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
public class NonOfficialPlanDTO {
  private int nonOfficialPlanId;
  private String memberEmail;
  private String planName;
  private Date startDate;
  private Date endDate;
  private Time authTime;
  private Date regTime;

  public NonOfficialPlanDTO(String memberEmail, String planName, String startDate, String endDate, String authTime) {
    this.memberEmail = memberEmail;
    this.planName = planName;
    this.startDate = DateUtil.toSqlDate(startDate);
    this.endDate = DateUtil.toSqlDate(endDate);
    this.authTime = DateUtil.toSqlTime(authTime);
  }
}
