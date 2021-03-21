package aop.service;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Aspect
@Log4j2
@Component
public class AlarmAdvice {
    @Before("execution(* component.alarm.AlarmService.*(..))")
    public void beforeSchoolService() {
        log.info("-----------------alarmService called-----------------");
    }

    @Around("execution(* component.alarm.AlarmService.deleteAlarm(..))")
    public Object beforeDeleteAlarm(ProceedingJoinPoint proceedingJoinPoint) {
        Object[] parameters = proceedingJoinPoint.getArgs();
        Arrays.stream(parameters).forEach(parameter -> log.info(parameter.toString()));

        return parameters[0];
    }
}
