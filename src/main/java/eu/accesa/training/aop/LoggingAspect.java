package eu.accesa.training.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(LogExecutionTime)") //advice - apply advice to all methods annotated with @LogExecutionTime
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startExecutionTime = System.currentTimeMillis();

        Object result =  joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - startExecutionTime;
        log.info("{} executed in : {} ", joinPoint.getSignature(), executionTime);

        return result;
    }

}

