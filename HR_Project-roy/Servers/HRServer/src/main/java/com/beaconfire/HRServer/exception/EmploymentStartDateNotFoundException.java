package com.beaconfire.HRServer.exception;

import com.beaconfire.HRServer.domain.Employee;

public class EmploymentStartDateNotFoundException extends Exception{
    public EmploymentStartDateNotFoundException(String s){
        super(s);
    }
}
