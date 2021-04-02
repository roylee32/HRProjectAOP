package com.beaconfire.HRServer.LoggingAOPOliverLi;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.mapping.Join;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("com.beaconfire.HRServer.LoggingAOPOliverLi.PointCuts.AllDAOs()")
    public Object executionTimeAdviceFromALLDAOs(ProceedingJoinPoint pjp) throws Throwable{
        long startTime = System.currentTimeMillis();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        Object result = pjp.proceed();
        long executionTime = System.currentTimeMillis() - startTime;
        logger.info("From AllDAOs PointCut: " + className + "." + methodName + " execution time: " + executionTime + " ms");
        return result;
    }

    @Around("com.beaconfire.HRServer.LoggingAOPOliverLi.PointCuts.EmployeeDAODataAccessor()")
    public Object executionTimeAdviceFromEmployeeDAODataAccessor(ProceedingJoinPoint pjp) throws Throwable{
        long startTime = System.currentTimeMillis();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        Object result = pjp.proceed();
        long executionTime = System.currentTimeMillis() - startTime;
        logger.info("From EmployeeDAODataAccessor PointCut: " + className + "." + methodName + " execution time: " + executionTime + " ms");
        return result;
    }

    @Around("com.beaconfire.HRServer.LoggingAOPOliverLi.PointCuts.HousingDAOBean()")
    public Object executionTimeAdviceFromHousingDAOBean(ProceedingJoinPoint pjp) throws Throwable{
        long startTime = System.currentTimeMillis();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        Object result = pjp.proceed();
        long executionTime = System.currentTimeMillis() - startTime;
        logger.info("From HousingDAOBean PointCut: " + className + "." + methodName + " execution time: " + executionTime + " ms");
        return result;
    }

    @Before("com.beaconfire.HRServer.LoggingAOPOliverLi.PointCuts.AllEndpoints()")
    public void beforeEndpoint(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        for (Object obj : args){
            sb.append(obj.toString());
        }
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("Request for " + className + "." + methodName + ": " + sb.toString());
    }

    @AfterReturning(value = "com.beaconfire.HRServer.LoggingAOPOliverLi.PointCuts.AllEndpoints()", returning = "response")
    public void afterEndpoints(Object response){
        logger.info("Response: " + response.toString());
    }

}
