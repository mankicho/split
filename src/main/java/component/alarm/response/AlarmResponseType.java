package component.alarm.response;

public enum AlarmResponseType {
    SuccessFulUpdateData(202, "successful update data"),
    SuccessFulDeleteData(203, "successful delete data"),
    NoDataToUpdate(204, "there is no data to update"),
    ServerError(500, "server error"),
    DontHaveTheAlarm(501, "you have not the alarm");
    int status;
    String msg;

    AlarmResponseType(int status, String msg) {
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
