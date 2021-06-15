package com.beaconfire.HRServer.service;

import com.beaconfire.HRServer.dao.EmployeeDAO;
import com.beaconfire.HRServer.dao.UserDAO;
import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeDAO employeeDAO;
    private UserDAO userDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public Integer addNewEmployee(String uid, String firstName, String lastName, String middleName, String cellPhone,
                                  String alternatePhone, String carInfo, String ssn, String DOB, String gender, String DL,
                                  String DLExpiration){
        User user = userDAO.getUserById(Integer.valueOf(uid));
        return employeeDAO.addNewEmployee(user, user.getEmail(), firstName, lastName, middleName,
                cellPhone, alternatePhone, carInfo, ssn, DOB, gender, DL, DLExpiration);
    }

    @Transactional
    public void setAvatar(String eid, String avatarPath){
        employeeDAO.updateAvatar(Integer.valueOf(eid), avatarPath);
    }

    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
    @Transactional
    public void updateEmployee(Integer eid, String firstName, String lastName, String middleName, String cellPhone,
                               String alternatePhone, String carInfo, String ssn, String DOB, String gender, String DL,
                               String DLExpiration){
        employeeDAO.updateEmployee(eid, firstName, lastName, middleName, cellPhone, alternatePhone, carInfo,
                ssn, DOB, gender, DL, DLExpiration);
    }

    @Transactional
    public Employee getEmployeeByUid(Integer uid){
        User u = userDAO.getUserById(uid);
        return employeeDAO.getEmployeeByUser(u).get(0);
    }
}
