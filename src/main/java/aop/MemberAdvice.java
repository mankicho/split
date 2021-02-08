package aop;

import component.member.MemberDTO;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

}
