package com.beaconfire.HRServer.service;

import com.beaconfire.HRServer.dao.ContactDAO;
import com.beaconfire.HRServer.dao.EmployeeDAO;
import com.beaconfire.HRServer.domain.Contact;
import com.beaconfire.HRServer.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactService {
    private ContactDAO contactDAO;
    private EmployeeDAO employeeDAO;

    @Autowired
    public void setContactDAO(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Transactional
    public Integer addNewEmergenceContact(Integer employeeId, String firstName, String lastName, String middleName,
                                          String phoneNumber, String email, String address, String relationship){
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        return contactDAO.addNewContact(employee, firstName, lastName, middleName, phoneNumber, email,
                address, relationship, false, false, true);
    }

    @Transactional
    public Integer addNewReferenceContact(Integer employeeId, String firstName, String lastName, String middleName,
                                          String phoneNumber, String email, String address, String relationship){
        Employee employee = employeeDAO.getEmployeeById((employeeId));
        return contactDAO.addNewContact(employee, firstName, lastName, middleName, phoneNumber, email,
                address, relationship, false, true, false);
    }

    @Transactional
    public Integer addNewLandlord(String firstName, String lastName, String middleName,
                                          String phoneNumber, String email, String address, String relationship){
        return contactDAO.addNewContact(null, firstName, lastName, middleName, phoneNumber, email,
                address, relationship, true, false, false);
    }

    @Transactional
    public List<Contact> getReferenceContactByEmployee(Employee employee){
        return contactDAO.getReferenceContactByEmployee(employee);
    }

    @Transactional
    public List<Contact> getEmergenceContactByEmployee(Employee employee){
        return contactDAO.getEmergenceContactByEmployee(employee);
    }

    @Transactional
    public void updateContact(Integer cid, String firstName, String lastName, String middleName,
                              String phoneNumber, String email, String address, String relationship){
        contactDAO.updateContact(cid, firstName, lastName, middleName, phoneNumber, email, address, relationship);
    }
}
