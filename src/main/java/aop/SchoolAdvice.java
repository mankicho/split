package aop;

import component.school.dto.ClassJoinDTO;
import component.school.explorer.dto.SchoolExplorerDTO;
import component.school.explorer.dto.SchoolExplorerRewardDTO;
import exception.error.SchoolErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import security.token.TokenGeneratorService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Log4j2
@Component
@RequiredArgsConstructor
public class SchoolAdvice {
    private final TokenGeneratorService tokenGeneratorService;

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
        if (categoryId < 0 || categoryId > 10) {
            log.info(SchoolErrorCode.OutOfRangeError + "--> " + categoryId);
        }
        log.info("client select schools of category[" + categoryId + "]");
    }

    @Before("execution(* controller.SchoolController.classAuthDo(..))")
    public void setClassAuthDTO(JoinPoint joinPoint) {
        Object[] parameters = joinPoint.getArgs();

        HttpServletRequest request = (HttpServletRequest) parameters[0];

    }

    @Around("execution(* component.school.SchoolService.*(..))")
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

    @Around("execution(* component.school.SchoolService.joinClass(..))")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] parameters = proceedingJoinPoint.getArgs();
        ClassJoinDTO dto = (ClassJoinDTO) parameters[0];
        int type = (int) parameters[1];
        if (type == 0) {
            log.info("nonOfficial class join request ==> " + dto.toString());
        } else {
            log.info("official class join request ==> " + dto.toString());
        }

        Object object = proceedingJoinPoint.proceed();
        int affectedRow = (int) object;

        if (affectedRow == 0) { // 가입 실패시
            log.info("class join fail");
        } else if (affectedRow == -1) {
            log.info("class join fail ==> reservation date is same or before with today");
        } else {
            log.info("class join success : insertedRow = [" + affectedRow + "]");
        }

        return object;
    }

    @Around("execution(* component.school.SchoolService.getAttendanceList(..))")
    public Object aroundGetExplorerVOAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] parameters = proceedingJoinPoint.getArgs();
        SchoolExplorerDTO dto = (SchoolExplorerDTO) parameters[0];
        log.info(dto);

        Object returnValue = proceedingJoinPoint.proceed();

        log.info(returnValue);
        return returnValue;
    }

    @Around("execution(* component.school.SchoolService.*(..))")
    public Object getExploreAttendanceRateAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnValue = proceedingJoinPoint.proceed();
        log.info(returnValue);
        return returnValue;
    }


    @Before("execution(* component.school.SchoolService.getExplorerReward(..))")
    public void getExplorerRewardAdvice(JoinPoint joinPoint) throws Throwable {
        Object[] parameters = joinPoint.getArgs();
        log.info(parameters[0].toString());
        SchoolExplorerRewardDTO schoolExplorerRewardDTO = (SchoolExplorerRewardDTO) parameters[0];
    }

    @AfterThrowing(value = "execution(* component.school.SchoolService.joinClass(..))", throwing = "e")
    public void logErrorAboutJoinClass(Exception e) {
        log.info(e.getMessage());
    }

}