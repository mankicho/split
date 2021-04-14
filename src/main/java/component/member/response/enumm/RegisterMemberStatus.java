package component.member.response.enumm;

public enum RegisterMemberStatus {
    AllOK(202, 202),
    MemberRegisterOKFileUploadFail(202, 500),
    MemberRegisterFailFileUploadOK(500, 202),
    MemberRegisterOKInvalidFileFormat(202, -1),
    MemberRegisterFailInvalidFileFormat(500, -1),
    AllFail(500, 500);

    private int registerStatus;
    private int fileStatus;

    RegisterMemberStatus(int registerStatus, int fileStatus) {
        this.registerStatus = registerStatus;
        this.fileStatus = fileStatus;
    }

    public int getRegisterStatus() {
        return registerStatus;
    }

    public int getFileStatus() {
        return fileStatus;
    }

    public static RegisterMemberStatus getRegisterMemberStatus(int registerStatus, int fileStatus) {

        if (fileStatus == -1) {
            if (registerStatus == 202) {
                return MemberRegisterOKInvalidFileFormat;
            } else {
                return MemberRegisterFailInvalidFileFormat;
            }
        }
        if (registerStatus == 202) {
            if (fileStatus == 202) {
                return AllOK;
            } else {
                return MemberRegisterOKFileUploadFail;
            }
        } else {
            if (fileStatus == 202) {
                return MemberRegisterFailFileUploadOK;
            } else {
                return AllFail;
            }
        }
    }
}