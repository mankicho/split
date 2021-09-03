package aop;

import component.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;

@Aspect
@Log4j2
@Component
@RequiredArgsConstructor
public class MemberAdvice {

    private final BCryptPasswordEncoder passwordEncoder; // password encoding function

    @Before("execution(* component.member.MemberService.registerMember(..))")
    public void passwordEncoding(JoinPoint joinPoint) {
        Object[] obj = joinPoint.getArgs();
        MemberDTO memberDTO = (MemberDTO) obj[0];
        memberDTO.setPw(passwordEncoder.encode(memberDTO.getPw()));

        log.info("memberDTO = " + memberDTO);

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


    @Around("execution(* component.member.MemberService.autoLogin(..))")
    public Object aroundMemberLogin(ProceedingJoinPoint pjp) throws Throwable {
        Object[] parameters = pjp.getArgs();
        String email = (String) parameters[0];
        log.info("login request ==> [" + email + "]");
        Object result = pjp.proceed();

        log.info(email + " member login");
        return result;
    }


}

