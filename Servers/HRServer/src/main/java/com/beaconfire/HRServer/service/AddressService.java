package com.beaconfire.HRServer.service;

import com.beaconfire.HRServer.dao.AddressDAO;
import com.beaconfire.HRServer.dao.EmployeeDAO;
import com.beaconfire.HRServer.domain.Address;
import com.beaconfire.HRServer.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {
    AddressDAO addressDAO;
    EmployeeDAO employeeDAO;

    @Autowired
    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Transactional
    public Integer addNewAddress(Integer employeeId, String addressLine1, String addressLine2, String city, String zipcode, String state){
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        return addressDAO.createAddress(employee, addressLine1, addressLine2, city, zipcode, state);
    }

    @Transactional
    public List<Address> getAddressByEmployee(Employee employee){
        return addressDAO.getAddressByEmployee(employee);
    }

    @Transactional
    public void updateAddress(Integer aid, String addressLine1, String addressLine2, String city, String zipcode, String state){
        addressDAO.updateAddress(aid, addressLine1, addressLine2, city, zipcode, state);
    }
}
