package com.saucedemo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.saucedemo..*.*(..))")
    public void allMethods() {}

    @Before("allMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        logger.info("==> Entering method: {} with arguments: {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "allMethods()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        logger.info("<== Exiting method: {} with result: {}", joinPoint.getSignature().toShortString(), result);
    }
}
