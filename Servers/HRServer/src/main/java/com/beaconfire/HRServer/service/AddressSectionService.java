package com.beaconfire.HRServer.service;

import com.beaconfire.HRServer.dao.AddressDAO;
import com.beaconfire.HRServer.dao.EmployeeDAO;
import com.beaconfire.HRServer.dao.UserDAO;
import com.beaconfire.HRServer.domain.Address;
import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressSectionService {

    EmployeeDAO employeeDAO;
    UserDAO userDAO;
    AddressDAO addressDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Transactional
    public Address getAddressByUserId(int uid) {
        User user = userDAO.getUserById(uid);
        Employee employee = employeeDAO.getEmployeeByUser(user).get(0);
        List<Address> res = addressDAO.getAddressByEmployee(employee);
        return res.size() == 0? null : res.get(0);
    }

    @Transactional
    public Address getAddressByEmployeeId(int eid) {
        Employee employee = employeeDAO.getEmployeeById(eid);
        List<Address> res = addressDAO.getAddressByEmployee(employee);
        return res.size() == 0? null : res.get(0);
    }

    @Transactional
    public void updateAddressByUserId(int eid, String line1, String line2, String city, String state, String zipcode) {
        Address address = getAddressByEmployeeId(eid);
        address.setAddressLine1(line1);
        address.setAddressLine2(line2);
        address.setCity(city);
        address.setStateName(state);
        address.setZipcode(zipcode);
        addressDAO.updateAddress(address);
    }
}
