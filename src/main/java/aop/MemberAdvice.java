package aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;

@Aspect
@Log4j
@Component
public class MemberAdvice {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Before("execution(* component.member.MemberService.*(..))")
    public void beforeMemberService() {
        log.info("-----------------memberService called-----------------");
    }

    @After("execution(* component.member.MemberService.selects(..))")
    public void logAfterLogin(JoinPoint joinPoint) {
        Object[] parameters = joinPoint.getArgs();

        String email = (String) parameters[0];
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info(email + " login request: " + LocalDateTime.now());
    }

    @After("execution(* component.member.MemberService.insertTimer(..))")
    public void logAfterAddTimer(JoinPoint joinPoint) {
        Object[] parameters = joinPoint.getArgs();
        HashMap<String, Object> hashMap = (HashMap<String, Object>) parameters[0];
        log.info(LocalDateTime.now() + ": " + hashMap);
    }

    @Around("execution(* component.member.MemberService.*(..))")
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
