package component.sms;

public interface SMSService {
    int sendSMSForReg(String phoneNumber);

    int sendSMSForFindEmail(String phoneNumber);

}
