package aop;


import component.plan.PlanDTO;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Aspect
@Log4j
@Component
public class PlanAdvice {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @After("execution(* component.plan.PlanService.deletePlan(..))")
    public void logAfterDeletePlan(JoinPoint joinPoint) {
        Object[] parameters = joinPoint.getArgs();

        Integer planLogId = (Integer) parameters[0];
        log.info("planLogId[" + planLogId + "] is deleted: " + LocalDateTime.now());
    }

    @After("execution(* component.plan.PlanService.insertPlan(..))")
    public void logBeforeInsertPlan(JoinPoint joinPoint) {
        Object[] parameters = joinPoint.getArgs();
        PlanDTO insertedPlan = (PlanDTO) parameters[0];
        log.info(insertedPlan.getMemberEmail()+" inserts plan["+insertedPlan.getPlanName()+"]");
    }
}
