package com.beaconfire.HRServer.dao;

import com.beaconfire.HRServer.domain.Address;
import com.beaconfire.HRServer.domain.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class AddressDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Integer createAddress(Employee employee, String addressLine1, String addressLine2, String city, String zipcode, String state){
        Session session = sessionFactory.getCurrentSession();
        Address newAddress = new Address();
        newAddress.setEmployee(employee);
        newAddress.setAddressLine1(addressLine1);
        newAddress.setAddressLine2(addressLine2);
        newAddress.setCity(city);
        newAddress.setZipcode(zipcode);
        newAddress.setStateName(state);
        return (Integer) session.save(newAddress);
    }

    public void updateAddress(Address address) {
        sessionFactory.getCurrentSession().update(address);
    }
    public void updateAddress(Integer aid, String addressLine1, String addressLine2, String city, String zipcode, String state){
        Session session = sessionFactory.getCurrentSession();
        Address address = session.get(Address.class, aid);
        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);
        address.setCity(city);
        address.setZipcode(zipcode);
        address.setStateName(state);
    }

    public List<Address> getAddressByEmployee(Employee employee){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from Address where employee = :employee";
        Query query = session.createQuery(statement);
        query.setParameter("employee", employee);
        return query.getResultList();
    }
}
