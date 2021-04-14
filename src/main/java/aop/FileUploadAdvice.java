package aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Aspect
@Log4j2
@Component
public class FileUploadAdvice {

    @Before("execution(* file.FileUploadService.*(..))")
    public void beforeDeleteAlarm() {
        log.info("-----------------fileUploadService called-----------------");
    }

    @Around("execution(* file.FileUploadService.*(..))")
    public Object aroundFileUpload(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] objects = proceedingJoinPoint.getArgs();
        MultipartFile multipartFile = (MultipartFile) objects[1];
        log.info("file upload request : " + multipartFile.getOriginalFilename());

        Object object = proceedingJoinPoint.proceed();

        log.info("upload success");

        return object;
    }

    @AfterThrowing(value = "execution(* file.FileUploadService.*(..))", throwing = "e")
    public void logErrorAboutFileUpload(Exception e) {
        log.info(e.getMessage());
    }
}
