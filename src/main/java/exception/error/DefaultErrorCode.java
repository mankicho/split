package exception.error;

public enum DefaultErrorCode {

    NullPointer(500, "invalid data(null)"),
    HttpMediaTypeNotSupportedException(501,"not supported content type"),
    JSONMapping(502,"invalid JSON data format"),
    NumberFormatException(503,"number formatting error"),
    SQLIntegrityConstraintViolationException(504,"server error(sql integrity constraint violation exception"),
    SQLSyntaxErrorException(505,"server error(sql syntax error)"),
    MissingParameterValue(506,"parameter value is missing"),
    IllegalDataException(507,"illegal data");
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
