package com.example.bai4.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class StudentAspect {
    @Before("execution(* com.example.bai4.controller.*.*(..))")
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

    @AfterThrowing(pointcut = "execution(* com.example.bai4.service.*.*(..))", throwing = "exception")
    public void logAfterThrow(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + ": " + exception.getMessage());
    }


    @Around("execution(* com.example.bai4.controller.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println(joinPoint.getSignature().getName() + " " + (end - start) + "ms");
        return result;
    }
}
