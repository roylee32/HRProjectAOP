package com.beaconfire.HRServer.dao;

import com.beaconfire.HRServer.domain.Contact;
import com.beaconfire.HRServer.domain.Employee;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ContactDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Contact getContactById(Integer id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Contact.class, id);
    }

    public Integer addNewContact(Employee employee, String firstName, String lastName, String middleName,
                                 String phoneNumber, String email, String address, String relationship,
                                 Boolean isLandlord, Boolean isReference, Boolean isEmergence){
        Session session = sessionFactory.getCurrentSession();
        Contact newContact = new Contact();
        newContact.setEmployee(employee);
        newContact.setFirstName(firstName);
        newContact.setLastName(lastName);
        newContact.setMiddleName(middleName);
        newContact.setPhoneNumber(phoneNumber);
        newContact.setEmail(email);
        newContact.setAddress(address);
        newContact.setRelationship(relationship);
        newContact.setLandlord(isLandlord);
        newContact.setReference(isReference);
        newContact.setEmergency(isEmergence);
        return (Integer) session.save(newContact);
    }


/*
    public List<Contact> getContactFromEmployee(Employee employee){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from Contact where employee = :employee";
*/

    public List<Contact> getContactFromEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        String statement = "from Contact where employee = :employee";
        Query query = session.createQuery(statement);
        query.setParameter("employee", employee);
        return query.getResultList();
    }

    public List<Contact> getReferenceContactByEmployee(Employee employee){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from Contact where employee = :employee and isReference = true";
        Query query = session.createQuery(statement);
        query.setParameter("employee", employee);
        return query.getResultList();
    }

    public List<Contact> getEmergenceContactByEmployee(Employee employee){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from Contact where employee = :employee and isEmergency = true";
        Query query = session.createQuery(statement);
        query.setParameter("employee", employee);
        return query.getResultList();
    }

    public void updateContact(Integer cid, String firstName, String lastName, String middleName,
                              String phoneNumber, String email, String address, String relationship){
        Session session = sessionFactory.getCurrentSession();
        Contact contact = session.get(Contact.class, cid);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setMiddleName(middleName);
        contact.setPhoneNumber(phoneNumber);
        contact.setEmail(email);
        contact.setAddress(address);
        contact.setRelationship(relationship);
    }
}
