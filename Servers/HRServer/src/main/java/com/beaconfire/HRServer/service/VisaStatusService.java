package com.beaconfire.HRServer.service;

import com.beaconfire.HRServer.dao.EmployeeDAO;
import com.beaconfire.HRServer.dao.UserDAO;
import com.beaconfire.HRServer.dao.VisaStatusDAO;
import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.VisaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VisaStatusService {
    private VisaStatusDAO visaStatusDAO;
    private EmployeeDAO employeeDAO;
    private UserDAO userDAO;

    @Autowired
    public void setVisaStatusDAO(VisaStatusDAO visaStatusDAO) {
        this.visaStatusDAO = visaStatusDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public Integer addNewVisaStatus(Integer employeeId, String visaType, String startDate, String endDate){
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        return visaStatusDAO.addNewVisaStatus(employee, visaType, startDate, endDate);
    }

    @Transactional
    public VisaStatus getVisaStatusByEmployeeId(int eid) {
        Employee employee = employeeDAO.getEmployeeById(eid);
        return visaStatusDAO.getVisaStatusByEmployee(employee).get(0);
    }

    @Transactional
    public List<VisaStatus> getVisaStatusByEmployee(Employee employee) {
        return visaStatusDAO.getVisaStatusByEmployee(employee);
    }

    @Transactional
    public void updateVisaStatus(VisaStatus visaStatus) {
        visaStatusDAO.updateVisaStatus(visaStatus);
    }

    @Transactional
    public List<VisaStatus> getAllVisaStatuses() {
        return visaStatusDAO.getAllVisaStatus();
    }

    @Transactional
    public void updateVisaStatus(Integer vid, String visaType, String startDate, String endDate){
        visaStatusDAO.updateVisaStatus(vid, visaType, startDate, endDate);
    }

}
