package exception.error;

import lombok.Data;

public enum SchoolErrorCode {
    DateMustBeFutureError(451, "date must be future"),
    ParseError(450, "invalid date"),
    FutureThanCurrentTimeError(452,"timestamp is ahead of current"),
    OutOfRangeError(453,"categoryId is out of range"),
    PositionNotMatchError(454,"user position and cafe position do not match"),
    DoNotHavePlansError(455,"do not have plans to certify plans"),
    NotProperTimeToAuthenticateError(456,"not proper time to authenticate"),
    DifferentFromDesignatedPlace(457,"different from the designated place");

    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    SchoolErrorCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SchoolErrorCode{" +
                "code=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
