
package com.beaconfire.HRServer.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCuts {


    @Pointcut("within(com.beaconfire.HRServer.controller.*)")
    public void inWebLayer() {}


    @Pointcut("within(com.beaconfire.HRServer.service..*)")
    public void inServiceLayer() {}


    @Pointcut("within(com.beaconfire.HRServer.dao..*)")
    public void inDataAccessLayer() {}


    @Pointcut("execution(* com.beaconfire.HRServer.service.*.*(..))")
    public void businessService() {}


    @Pointcut("execution(* com.beaconfire.HRServer.dao.*.getUserName*(..))")
    public void dataAccessOperation() {}
}
