package com.uba.check_book.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    // Controllers
    @Pointcut("within(com.uba.check_book.controller..*)")
    public void controllerLayer() {}

    // Services
    @Pointcut("within(com.uba.check_book.service..*)")
    public void serviceLayer() {}

    // Repositories
    @Pointcut("within(com.uba.check_book.repository..*)")
    public void repositoryLayer() {}

    // -------------------- BEFORE --------------------
    @Before("controllerLayer() || serviceLayer() || repositoryLayer()")
    public void logBefore(JoinPoint joinPoint) {
        String correlationId = MDC.get(CorrelationIdFilter.CORRELATION_ID);
        log.info("[{}] >> Entering {}.{} with args = {}",
                correlationId,
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    // -------------------- AFTER RETURNING --------------------
    @AfterReturning(pointcut = "controllerLayer() || serviceLayer() || repositoryLayer()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String correlationId = MDC.get(CorrelationIdFilter.CORRELATION_ID);
        log.info("[{}] << Exiting {}.{} with result = {}",
                correlationId,
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                result);
    }

    // -------------------- AFTER THROWING --------------------
    @AfterThrowing(pointcut = "controllerLayer() || serviceLayer() || repositoryLayer()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        String correlationId = MDC.get(CorrelationIdFilter.CORRELATION_ID);
        log.error("[{}] !! Exception in {}.{}: {}",
                correlationId,
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                ex.getMessage(),
                ex);
    }
}