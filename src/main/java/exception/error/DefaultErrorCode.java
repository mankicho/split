package exception.error;

public enum DefaultErrorCode {
    MissingParameterValue(454,"parameter value is missing"),
    NullPointer(500, "invalid data(null)"),
    HttpMediaTypeNotSupportedException(501,"not supported content type"),
    JSONMapping(502,"invalid JSON data format");
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
