package aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Aspect
@Log4j2
@Component
public class SMSAdvice {
    @Before("execution(* component.sms.SMSService.*(..))")
    public void beforeMemberService() {
        log.info("-----------------smsService called-----------------");
    }

    @After("execution(* component.sms.SMSService.*(..))")
    public void logAfterLogin(JoinPoint joinPoint) {
        Object[] parameters = joinPoint.getArgs();
        String phoneNumber = (String) parameters[0];
        log.info("SMSService["+LocalDateTime.now() + "] parameter : " + phoneNumber);
    }

}
