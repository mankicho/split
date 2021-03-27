package component.home.vo;

import lombok.Data;

import java.util.Date;

@Data
public class HomeExplorerVO {
    private int schoolId;
    private int schoolType;
    private String schoolName;
    private String setLocation;
    private String imagePath;
    private int myNeedTotalCheckNum;
    private int classId;
    private int setPaymentAmount;
    private int myTotalCheckNum;
    private int todayReservedPerson;
    private Date dummyDate;
}
