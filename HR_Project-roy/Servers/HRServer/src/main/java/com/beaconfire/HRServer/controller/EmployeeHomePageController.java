package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.response.EmployeeHomePageResponse;
import com.beaconfire.HRServer.service.EmployeeHomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeHomePageController {

    EmployeeHomePageService employeeHomePageService;

    @Autowired
    public void setEmployeeHomePageService(EmployeeHomePageService employeeHomePageService){
        this.employeeHomePageService = employeeHomePageService;
    }

    @GetMapping(value="/hr/api/employeeHomePage/employeeInfo")
    public EmployeeHomePageResponse getEmployeeHomePageInfo(@RequestParam String eid){
        EmployeeHomePageResponse response = new EmployeeHomePageResponse();
        Employee employee = employeeHomePageService.getEmployeeInfo(Integer.parseInt(eid));
        response.setEmployee(employee);
        return response;
    }
}
