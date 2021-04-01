package com.beaconfire.HRServer.service;

import com.beaconfire.HRServer.dao.ContactDAO;
import com.beaconfire.HRServer.dao.EmployeeDAO;
import com.beaconfire.HRServer.dao.UserDAO;
import com.beaconfire.HRServer.dao.VisaStatusDAO;
import com.beaconfire.HRServer.domain.Contact;
import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.User;
import com.beaconfire.HRServer.domain.VisaStatus;
import com.beaconfire.HRServer.exception.EmploymentStartDateNotFoundException;
import com.beaconfire.HRServer.util.DatetimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionScoped;
import java.util.ArrayList;
import java.util.List;


@Service
public class NameSectionService {
    EmployeeDAO employeeDao;
    UserDAO userDao;
    VisaStatusDAO visaStatusDao;
    ContactDAO contactDao;

    @Autowired
    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setEmployeeDao(EmployeeDAO employeeDao) {this.employeeDao = employeeDao;}

    @Autowired
    public void setVisaStatusDao(VisaStatusDAO visaStatusDao) {this.visaStatusDao = visaStatusDao;}

    @Autowired
    public void setContactDao(ContactDAO contactDao) {this.contactDao = contactDao;}

    @Transactional
    public Employee getEmployeeInfoByUID(int uid){
        User user = userDao.getUserById(uid);
        Employee employee = employeeDao.getEmployeeByUser(user).get(0);
        return employee;
    }
    @Transactional
    public Employee getEmployeeInfoByEID(Integer eid) throws EmploymentStartDateNotFoundException{
        Employee employee = employeeDao.getEmployeeById(eid);

        if(employee.getStartDate() == null){
            throw new EmploymentStartDateNotFoundException("Work date not found");
        }


        return employee;
    }

    @Transactional
    public void updateEmployeeNameSectionInfo(String firstName, String middleName, String lastName, String birthday, String gender, Integer eid) {
        //User user = userDao.getUserById1(uid).get(0);
        Employee employee = employeeDao.getEmployeeById(eid);
        employeeDao.updateEmployeeNameSection(firstName, middleName, lastName, birthday, gender, employee);
    }


    @Transactional
    public void updateEmployeeContactInfoSectionInfo(String email, String cellPhone, String workPhone, Integer eid) {
        Employee employee = employeeDao.getEmployeeById(eid);
        employeeDao.updateEmployeeContactInfoSection(email, cellPhone, workPhone, employee);
    }


    @Transactional
    public VisaStatus getVisaStatusByEmployeeID(Integer eid){
        //User user = userDao.getUserById1(uid).get(0);
        Employee employee = employeeDao.getEmployeeById(eid);
        System.out.println(employee.getEid());
        List<VisaStatus> resList = visaStatusDao.getVisaStatusByEmployee(employee);
        VisaStatus visaStatus = resList.size() == 0? null :resList.get(0);
        return visaStatus;
    }


    @Transactional
    public void updateEmployeeEmploymentInfoSection(String workAuth, String workStartDate, String workEndDate, String employmentStartDate, String employmentEndDate, String title, Integer eid){
        //User user = userDao.getUserById1(uid).get(0);
        Employee employee = employeeDao.getEmployeeById(eid);
        employeeDao.updateEmployeeEmploymentInfoSection(employmentStartDate, employmentEndDate, title, employee);
        VisaStatus visaStatus = visaStatusDao.getVisaStatusByEmployee(employee).get(0);
        visaStatusDao.updateVisaStatusEmploymentSection(workAuth, workStartDate, workEndDate, visaStatus);
    }


    @Transactional
    public List<Contact> getContactsFromUid(Employee employee) {
        List<Contact> contactList = new ArrayList<Contact>();
        contactList = contactDao.getEmergenceContactByEmployee(employee);
        return contactList;
    }



}
