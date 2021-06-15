package com.beaconfire.HRServer.service;

import com.beaconfire.HRServer.dao.EmployeeDAO;
import com.beaconfire.HRServer.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeHomePageService {
    EmployeeDAO employeeDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO){this.employeeDAO = employeeDAO;}

    @Transactional
    public Employee getEmployeeInfo(Integer eid){
        return employeeDAO.getEmployeeById(eid);
    }
}
