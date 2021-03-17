package exception.error;

public enum DefaultErrorCode {
    missingParameterValue(454,"parameter value is missing"),
    NullPointer(500, "invalid data(null)");
    private int code;
    private String msg;

    DefaultErrorCode(int code, String msg) {
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
