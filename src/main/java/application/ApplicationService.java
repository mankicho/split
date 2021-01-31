package application;

import component.sms.SMSService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService implements ApplicationListener<ContextClosedEvent> {

    @Setter(onMethod_ = {@Autowired})
    private SMSService smsService;

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        smsService.sendSMSToAdmin("01028470440");
    }
}
