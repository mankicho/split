package aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Log4j
@Component
public class PlanAdvice {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Before("execution(* component.plan.off.OfficialPlanService*.*(..))")
    public void logBefore() {
        log.info("--------------------");
    }

    @Before("execution(* component.plan.off.OfficialPlanService*.select())")
    public void logBeforeSelects() {
        log.info(dateFormat.format(new Date()) + ": OfficialPlanService.selects() called");
    }

}

