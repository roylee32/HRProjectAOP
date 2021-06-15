package com.beaconfire.HRServer.dao;

import com.beaconfire.HRServer.domain.*;

import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.User;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Employee> getEmployeeByUser(User u){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from Employee where user = :user";
        Query query = session.createQuery(statement);
        query.setParameter("user", u);
        return query.getResultList();
    }

    public void updateEmployeeNameSection(String firstName, String middleName, String lastName, String birthday, String gender, Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        employee.setFirstName(firstName);
        employee.setMiddleName(middleName);
        employee.setLastName(lastName);
        employee.setDOB(birthday);
        employee.setGender(gender);
        session.update(employee);
    }
    public void updateEmployeeContactInfoSection(String email, String cellPhone, String workPhone, Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        employee.setEmail(email);
        employee.setCellPhone(cellPhone);
        employee.setAlternatePhone(workPhone);
        session.update(employee);
    }

    public Employee getEmployeeById(Integer id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    public void updateEmployeeEmploymentInfoSection(String startDate, String endDate, String title, Employee employee){
        Session session = sessionFactory.getCurrentSession();
        employee.setStartDate(startDate);
        employee.setEndDate(endDate);
        employee.setTitle(title);
        session.update(employee);
    }

    public Integer addNewEmployee(User user, String email, String firstName, String lastName, String middleName, String cellPhone,
                                  String alternatePhone, String carInfo, String ssn, String DOB, String gender, String DL,
                                  String DLExpiration){
        Session session = sessionFactory.getCurrentSession();
        House house = session.get(House.class, 2);
        Employee newEmployee = new Employee();
        newEmployee.setUser(user);
        newEmployee.setEmail(email);
        newEmployee.setFirstName(firstName);
        newEmployee.setLastName(lastName);
        newEmployee.setMiddleName(middleName);
        newEmployee.setCellPhone(cellPhone);
        newEmployee.setAlternatePhone(alternatePhone);
        newEmployee.setCar(carInfo);
        newEmployee.setSSN(ssn);
        newEmployee.setDOB(DOB);
        newEmployee.setGender(gender);
        newEmployee.setDLNumber(DL);
        newEmployee.setDLExpiration(DLExpiration);
        newEmployee.setAvartar("default_avatar.png");
        newEmployee.setHouse(house);
        return (Integer) session.save(newEmployee);
    }

    public void updateEmployee(Integer eid, String firstName, String lastName, String middleName, String cellPhone,
                               String alternatePhone, String carInfo, String ssn, String DOB, String gender, String DL,
                               String DLExpiration){
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, eid);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setMiddleName(middleName);
        employee.setCellPhone(cellPhone);
        employee.setAlternatePhone(alternatePhone);
        employee.setCar(carInfo);
        employee.setSSN(ssn);
        employee.setDOB(DOB);
        employee.setGender(gender);
        employee.setDLNumber(DL);
        employee.setDLExpiration(DLExpiration);

    }

    public void updateAvatar(Integer eid, String avatarPath){
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, eid);
        employee.setAvartar(avatarPath);
    }

    public Employee getEmployeeByEmail(String email){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from Employee where email = :email";
        Query query = session.createQuery(statement);
        query.setParameter("email", email);
        List<Employee> employees = query.getResultList();
        if (employees.isEmpty()){
            return null;
        }
        return employees.get(0);
    }

    public List<Employee> getAllEmployees() {
        String statement = "FROM Employee";
        Query query = sessionFactory.getCurrentSession().createQuery(statement);
        return query.getResultList();
    }
}
