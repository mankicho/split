package aop;

import component.home.HomeDataDTO;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Log4j2
@Component
public class HomeDataAdvice {

    @Before("execution(* component.home.HomeDataService.*(..))")
    public void beforeHomeDataService() {
        log.info("-----------------homeDataService called-----------------");
    }

    @Before("execution(* component.home.HomeDataService.getHomeData(..))")
    public void getHomeDataAdvice(JoinPoint joinPoint) {
        Object[] parameters = joinPoint.getArgs();
        HomeDataDTO homeDataDTO = (HomeDataDTO) parameters[0];

        log.info("homeDataDTO =" + homeDataDTO);
    }

    @Around("execution(* component.home.HomeDataService.*(..))")
    public Object timeToProcess(ProceedingJoinPoint pjp) throws Throwable {
        // before advice
        StopWatch sw = new StopWatch();
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
        sw = null;
        return result;
    }
}
