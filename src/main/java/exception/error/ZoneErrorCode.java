package exception.error;

public enum ZoneErrorCode {
    ;

    private int status;
    private String msg;

    ZoneErrorCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
