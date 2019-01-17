package com.liangjinhai.studydemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class TimeAspect {

    @Pointcut("execution(* com.liangjinhai.studydemo.controller.TestController.test(..))")
    public void test(){

    }

    @Before("test()")
    public void testBefore(JoinPoint joinPoint){
        System.out.println("this is aspect before .......................");
        String classname = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("@before Execute! --class name: " + classname + ", method name: " + methodName + " " + args );
    }

    @Around("test()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("this is aspect around .......................");
        System.out.println("@Around：执行目标方法之前...");
        long start = System.currentTimeMillis();
        Object[] args = pjp.getArgs();//参数列表
        for(Object arg : args){
            System.out.println("arg is: "+arg);
        }
        Object proseed = pjp.proceed();
        System.out.println("aspect 耗时: " + (System.currentTimeMillis()-start)/1000.0);
        System.out.println("aspect end");
        return proseed;
    }

    @After("test()")
    public void testAfter(JoinPoint point){
        System.out.println("this is aspect after .......................");
        System.out.println("@After：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@After：被织入的目标对象为：" + point.getTarget());
    }

    @AfterThrowing("test()")
    public void testException(){
        System.out.println("异常通知 .......................");
    }

    @AfterReturning("test()")
    public void testReturn(JoinPoint point){
        System.out.println("this is aspect afterReturn .......................");
        System.out.println("@AfterReturning：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        System.out.println("@AfterReturning：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@AfterReturning：返回值为：" );
        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
    }

}
