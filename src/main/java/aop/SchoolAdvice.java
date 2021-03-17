package aop;

import exception.error.SchoolErrorCode;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Log4j2
@Component
public class SchoolAdvice {
    private StopWatch sw = new StopWatch();

    @Before("execution(* component.school.SchoolService.*(..))")

    public void beforeSchoolService() {
        log.info("-----------------schoolService called-----------------");
    }

    @Before("execution(* component.school.SchoolService.getSchools(..))")
    public void logAfterGetSchools(JoinPoint joinPoint) {
        Object[] parameters = joinPoint.getArgs();

        log.info(Arrays.toString(parameters));
        if (parameters == null || parameters.length == 0) {
            return;
        }

        int categoryId = (int) parameters[0];
        if (categoryId < 0 || categoryId > 5) {
            log.info(SchoolErrorCode.OutOfRangeError + "--> " + categoryId);
        }
        log.info("client select schools of category[" + categoryId + "]");
    }

    @Around("execution(* component.school.SchoolService.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        // before advice
        sw.start();

        Object result = pjp.proceed();

        // after advice
        sw.stop();
        long total = sw.getTotalTimeMillis();

        // 어떤 클래스의 메서드인지 출력하는 정보는 pjp 객체에 있다.
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String taskName = className + "." + methodName;


        // 실행시간은 로그로 남기는 것이 좋지만, 여기서는 콘솔창에 찍도록 한다.
        log.info("[ExecutionTime] " + taskName + " , " + total + "(ms)");

        return result;
    }

    @Before("execution(* component.school.SchoolService.*(..))")
    public void aroundAdvice(JoinPoint joinPoint) {

    }
}