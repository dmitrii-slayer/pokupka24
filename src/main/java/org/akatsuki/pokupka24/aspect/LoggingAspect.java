package org.akatsuki.pokupka24.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* org.akatsuki.pokupka24.domain.repository.*.*(..))")
    public Object logRepositoryAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();

        System.out.println("Начинается выполнение метода " + methodName);

        Object targetMethodResult = proceedingJoinPoint.proceed();

        System.out.println("Закончилось выполнение метода " + methodName);
        return targetMethodResult;
    }
}
