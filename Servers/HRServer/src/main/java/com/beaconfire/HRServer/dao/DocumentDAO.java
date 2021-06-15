package com.beaconfire.HRServer.dao;

import com.beaconfire.HRServer.domain.DigitalDocument;
import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.PersonalDocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Repository
public class DocumentDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<DigitalDocument> getAllDigitalDocuments(){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from DigitalDocument";
        Query query = session.createQuery(statement);
        return query.getResultList();
    }

    public Integer uploadPersonalDocumentForEmployee(Employee employee, String path, String title,
                                                     String comment, String createDate){
        Session session = sessionFactory.getCurrentSession();
        PersonalDocument document = new PersonalDocument();
        document.setEmployee(employee);
        document.setPath(path);
        document.setTitle(title);
        document.setComment(comment);
        document.setCreateDate(createDate);
        return (Integer) session.save(document);
    }

    public List<PersonalDocument> getPersonalDocumentByEmployeeId(Employee employee){
        Session session = sessionFactory.getCurrentSession();
//        Employee employee = session.get(PersonalDocument.class, employeeId);
//        employee.getDocuments();

        String statement = "from PersonalDocument where employee = :employeeId";
        Query query = session.createQuery(statement);
        query.setParameter("employeeId", employee);
        return query.getResultList();
    }


}
