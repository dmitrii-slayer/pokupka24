package org.akatsuki.pokupka24.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SleepAspect {

    // для тестирования логирования методов, которые выполняются > 100 мс
    @Before("execution(* org.akatsuki.pokupka24.rest.controller.*.find*(..))")
    public void sleepBeforeFindAdvice() {
        try {
            Thread.sleep(95L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
