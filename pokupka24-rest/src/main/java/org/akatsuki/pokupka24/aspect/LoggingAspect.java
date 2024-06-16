package org.akatsuki.pokupka24.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Value("${logging.executionTimeThreshold.controller}")
    private int executionTimeThreshold;

    @Around("execution(* org.akatsuki.pokupka24.rest.controller.*.*(..))")
    public Object logSlowEndpointsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object targetMethodResult = proceedingJoinPoint.proceed();

        long finish = System.currentTimeMillis();

        long executionTime = finish - start;
        if (executionTime > executionTimeThreshold) {
            MethodSignature method = (MethodSignature) proceedingJoinPoint.getSignature();
            log.info("Execution took {} ms for {}.{}", executionTime, method.getDeclaringTypeName(), method.getName());
        }
        return targetMethodResult;
    }
}
