package aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Log4j2
@Component
public class ZoneAdvice {

    @Before("execution(* component.zone.ZoneService.*(..))")
    public void beforeZoneServiceMapper() {
        log.info("-----------------zoneService called-----------------");
    }


}
