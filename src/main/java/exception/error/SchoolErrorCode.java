package exception.error;

import lombok.Data;

public enum SchoolErrorCode {
    DateMustBeFuture(451, "date must be future"),
    ParseError(450, "invalid date format");

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
