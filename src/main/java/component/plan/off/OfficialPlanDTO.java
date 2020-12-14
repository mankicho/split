package component.plan.off;

public class OfficialPlanDTO {
    private int planId;
    private String planName;
    private int needAuthNum;

    public OfficialPlanDTO(int planId, String planName, int needAuthNum) {
        this.planId = planId;
        this.planName = planName;
        this.needAuthNum = needAuthNum;
    }


    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getNeedAuthNum() {
        return needAuthNum;
    }

    public void setNeedAuthNum(int needAuthNum) {
        this.needAuthNum = needAuthNum;
    }
}
