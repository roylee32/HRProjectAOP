package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.VisaStatus;
import com.beaconfire.HRServer.response.HrEmployeeProfileResponse;
import com.beaconfire.HRServer.service.EmployeeService;
import com.beaconfire.HRServer.service.VisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HrEmployeeProfileController {

    private EmployeeService employeeService;

    private VisaStatusService visaStatusService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setVisaStatusService(VisaStatusService visaStatusService) {
        this.visaStatusService = visaStatusService;
    }

    @GetMapping(value = "hr/api/getAllEmployees")
    public HrEmployeeProfileResponse getAllEmployees() {
        HrEmployeeProfileResponse response = new HrEmployeeProfileResponse();
        List<Employee> allEmployees = employeeService.getAllEmployees();
        List<VisaStatus> allVisaStatuses = visaStatusService.getAllVisaStatuses();
        Map<Integer, VisaStatus> map = new HashMap<>();
        for (VisaStatus visaStatus : allVisaStatuses) {
            map.put(visaStatus.getEmployee().getEid(), visaStatus);
        }
        for (Employee employee : allEmployees) {
            VisaStatus visaStatus = map.getOrDefault(employee.getEid(), null);
            response.addProfile(employee, visaStatus);
        }
        return response;
    }
}
