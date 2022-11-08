package com.ljn.communityljn.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author li
 * @Date 11/8/22 3:21 PM
 * @Version 1.0
 * 描述 ：
 * 名称：AlphaAspect
 */
//@Component
//@Aspect
public class AlphaAspect {


    //切入点
    @Pointcut("execution(* com.ljn.communityljn.service.*.*(..))")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void before() {
        System.out.println("before");
    }

//    @After("pointcut()")
//    public void after() {
//        System.out.println("after");
//    }
//
//    @AfterReturning("pointcut()")
//    public void afterRetuning() {
//        System.out.println("afterRetuning");
//    }
//
//    @AfterThrowing("pointcut()")
//    public void afterThrowing() {
//        System.out.println("afterThrowing");
//    }
//
//    @Around("pointcut()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("around before");
//        Object obj = joinPoint.proceed();
//        System.out.println("around after");
//        return obj;
//    }

}
