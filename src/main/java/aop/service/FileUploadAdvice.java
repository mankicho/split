package aop.service;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Log4j2
@Component
public class FileUploadAdvice {

    @Before("execution(* file.FileUploadService.*(..))")
    public void beforeDeleteAlarm() {
        log.info("fileUpLoad service is called");
    }
}
