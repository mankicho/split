package aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Log4j
@Component
public class MemberAdvice {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Before("execution(* component.member.MemberService*.*(..))")
    public void logBefore() {
        log.info("----------------------------");
    }

    @Before("execution(* component.member.MemberService*.selects())")
    public void logBeforeSelects() {
        log.info(dateFormat.format(new Date()) + ": memberService.selects() called");
    }
}
