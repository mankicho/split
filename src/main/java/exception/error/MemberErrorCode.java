package exception.error;

public enum MemberErrorCode {
    SQLIntegrityConstraintViolationException(700,"duplicate entry for primary key");

    private int code;
    private String msg;

    MemberErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
