package com.example.bai1.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    @Before("execution(* com.example.bai1.service.BookService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        sb.append(methodName).append("(");
        for (int i = 0; i < args.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(args[i]);
        }
        sb.append(")");
        System.out.println(sb.toString());
    }


    @AfterReturning(value = "execution(* com.example.bai1.service.BookService.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + " -> " + result.toString());
    }


    @Around("execution(* com.example.bai1.controller.*Controller.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Time taken by " + joinPoint.getSignature().toShortString() + " is " + duration + " ms");
        return result;
    }
}
