package aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Log4j
@Component
public class FileUploadAdvice {

    @Before("execution(* component.peed.upload.)")
    public void logAfterDeletePlan(JoinPoint joinPoint) {
        Object[] parameters = joinPoint.getArgs();

        Integer planLogId = (Integer) parameters[0];
        log.info("planLogId[" + planLogId + "] is deleted: " + LocalDateTime.now());
    }
}
