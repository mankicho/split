package component.sms;

public interface SMSService {
    int sendSMS(String phoneNumber, String msg, int code);
}
