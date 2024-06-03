package org.akatsuki.pokupka24.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* org.akatsuki.pokupka24.domain.repository.*.*(..))")
    public Object logRepositoryAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();

        log.debug("Начинается выполнение метода {}", methodName);

        Object targetMethodResult = proceedingJoinPoint.proceed();

        log.debug("Закончилось выполнение метода {}", methodName);

        return targetMethodResult;
    }

    @Around("execution(* org.akatsuki.pokupka24.controller.*.*(..))")
    public Object logSlowEndpointsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object targetMethodResult = proceedingJoinPoint.proceed();

        long finish = System.currentTimeMillis();

        long duration = finish - start;
        if (duration > 100) {
            MethodSignature method = (MethodSignature) proceedingJoinPoint.getSignature();
            log.info("Execution took {} ms for {}.{}", duration, method.getDeclaringTypeName(), method.getName());
        }
        return targetMethodResult;
    }
}
