
package com.beaconfire.HRServer.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("com.beaconfire.HRServer.aop.PointCuts.inDataAccessLayer()")
    public Object executionTimeAdvice(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        Object result = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;

        System.out.println("ClassName: "+className);
        System.out.println("MethodName: "+methodName);
        System.out.println("Result: "+result);

        log.info(className+"."+methodName+" execution time: "+elapsedTime+" ms");
        return result;

    }

    @Before("within(com.beaconfire.HRServer.controller.NameSectionController) || within(com.beaconfire.HRServer.controller.EmployeeHomePageController)" )
    public void beforeAdvice(JoinPoint joinPoint){
        log.info("Before Advice: "+ joinPoint.getSignature());
    }


    @AfterReturning(value = "com.beaconfire.HRServer.aop.PointCuts.inDataAccessLayer()", returning = "res")
    public void afterReturningAdvice(Object res){
        log.info("After Returning: " + res.toString());
    }




/*
    @After("execution(com.beaconfire.HRServer.response.NameSectionController com.beaconfire.HRServer.controller..*(..))")
    public void afterAdvice(JoinPoint joinPoint){
        log.info("After Advice: "+ joinPoint.getSignature());
    }


*/
}
