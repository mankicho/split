package aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class MyPageAdvice {
    @Around("execution(* component.mypage.MyPageService.*(..))")
    public Object getHomeDataAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] parameters = proceedingJoinPoint.getArgs();

        Arrays.stream(parameters).forEach(parameter -> log.info(parameter.toString()));

        Object object = proceedingJoinPoint.proceed();

        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String taskName = className + "." + methodName;
        log.info(taskName+ " complete");
        return object;
    }
}
