package com.beaconfire.HRServer.LoggingAOPOliverLi;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCuts {

    @Pointcut("within(com.beaconfire.HRServer.dao.*)")
    public void AllDAOs(){}

    @Pointcut("execution(* com.beaconfire.HRServer.dao.EmployeeDAO.get*(..))")
    public void EmployeeDAODataAccessor(){}

    @Pointcut("bean(housingDAO)")
    public void HousingDAOBean(){}

    @Pointcut("within(com.beaconfire.HRServer.controller.*)")
    public void AllEndpoints(){}


}
