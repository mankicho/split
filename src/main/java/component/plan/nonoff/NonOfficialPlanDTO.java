package component.plan.nonoff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.DateUtil;

import java.sql.Date;
import java.sql.Time;

/**
 * insert
 */
@Data
public class NonOfficialPlanDTO {
  private String memberEmail;
  private String planName;
  private Date startDate;
  private Date endDate;
  private Time authTime;
}
