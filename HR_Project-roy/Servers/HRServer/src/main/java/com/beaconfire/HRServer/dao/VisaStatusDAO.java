package com.beaconfire.HRServer.dao;

import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.VisaStatus;
import com.beaconfire.HRServer.util.DatetimeUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class VisaStatusDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Integer addNewVisaStatus(Employee employee, String visaType, String startDate, String endDate){
        Session session = sessionFactory.getCurrentSession();
        VisaStatus visaStatus = new VisaStatus();
        visaStatus.setEmployee(employee);
        visaStatus.setVisaType(visaType);
        visaStatus.setVisaStartDate(startDate);
        visaStatus.setVisaEndDate(endDate);
        visaStatus.setActive(true);
        return (Integer) session.save(visaStatus);
    }


    /*
    public List<VisaStatus> getVisaStatus(Employee employee){
*/
    //public List<VisaStatus> getVisaStatusByEmployee(Employee employee){


//    public VisaStatus getVisaStatusByEmployee(Employee employee) {
//        String statement = "FROM VisaStatus WHERE employee = :employee";
//        Query query = sessionFactory.getCurrentSession().createQuery(statement);
//        query.setParameter("employee", employee);
//        List<VisaStatus> res = query.getResultList();
//        return res.size() > 0? res.get(0) : null;
//    }

    public void updateVisaStatus(VisaStatus visaStatus) {
        sessionFactory.getCurrentSession().update(visaStatus);
    }

    public List<VisaStatus> getVisaStatus(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        String statement = "from VisaStatus where employee = :employee";
        Query query = session.createQuery(statement);
        query.setParameter("employee", employee);
        return query.getResultList();
    }

    public List<VisaStatus> getVisaStatusByEmployee(Employee employee){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from VisaStatus where employee = :employee";
        Query query = session.createQuery(statement);
        query.setParameter("employee", employee);
        return query.getResultList();
    }

    public void updateVisaStatusEmploymentSection(String workAuth, String workAuthStart, String workAuthEnd, VisaStatus visaStatus){
        Session session = sessionFactory.getCurrentSession();
        visaStatus.setVisaType(workAuth);
        visaStatus.setVisaStartDate(workAuthStart);
        visaStatus.setVisaEndDate(workAuthEnd);
    }

    public List<VisaStatus> getAllVisaStatus(){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from VisaStatus";
        Query query = session.createQuery(statement);
        return query.getResultList();
    }

    public void updateVisaStatus(Integer vid, String visaType, String startDate, String endDate){
        Session session = sessionFactory.getCurrentSession();
        VisaStatus visaStatus = session.get(VisaStatus.class, vid);
        visaStatus.setVisaType(visaType);
        visaStatus.setVisaStartDate(startDate);
        visaStatus.setVisaEndDate(endDate);
        visaStatus.setModificationDate(DatetimeUtil.getCurrentDateTime());
    }

}
