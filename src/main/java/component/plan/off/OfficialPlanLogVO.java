package component.plan.off;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

/**
 * 유저의 플랜 예약 내역
 *
 */
@Data
public class OfficialPlanLogVO {
    private int planLogId;
    private String memberEmail;
    private int planId;
    private Date startDate;
    private Date endDate;
    private Time authTime;
    private Date regTime;
    private String planName;
    private int needAuthNum;
}
