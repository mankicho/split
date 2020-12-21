package aop;

import component.plan.off.OfficialPlanDTO;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

@Aspect
@Log4j
@Component
public class PlanAdvice {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Before("execution(* component.plan.off.OfficialPlanService*.*(..))")
    public void logBefore() {
        log.info("--------------------");
    }

    @After("execution(* component.plan.off.OfficialPlanService*.*(..))")
    public void logAfter() {
        log.info("--------------------");
    }

    @Before("execution(* component.plan.off.OfficialPlanService*.select())")
    public void logBeforeSelects() {
        log.info(dateFormat.format(new Date()) + ": OfficialPlanService.selects() called");
    }

    @Around("execution(* component.plan.off.OfficialPlanService*.insertOfficialPlan())")
    public void logBeforeInsertOfficialPlan(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("parameter : " + Arrays.toString(proceedingJoinPoint.getArgs()));
    }
}

