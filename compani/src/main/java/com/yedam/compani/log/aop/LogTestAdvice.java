package com.yedam.compani.log.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// Test를 위한 임시 class
@Aspect
@Component
public class LogTestAdvice {
    @Pointcut("within(*..*ServiceImpl)")
    public void allPointCut() {}
    
    @Around("allPointCut()")
    public Object logger(ProceedingJoinPoint joinpoint) throws Throwable {
        String signatureStr = joinpoint.getSignature().toString();
        System.out.println("시작 : " + signatureStr);
        
        System.out.println("기능 전 실행" + System.currentTimeMillis());
        
        try {
            Object obj = joinpoint.proceed();
            return obj;
        } finally {
            System.out.println("기능 후 실행 " + System.currentTimeMillis());   
        }
    }
}
