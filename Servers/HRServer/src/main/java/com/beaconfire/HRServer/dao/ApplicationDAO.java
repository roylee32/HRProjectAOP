package com.beaconfire.HRServer.dao;

import com.amazonaws.services.opsworks.model.App;
import com.beaconfire.HRServer.domain.Application;
import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.util.DatetimeUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ApplicationDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Integer addNewOnboardingApplication(Employee employee, String createDate, String status,
                                               String comments, String applicationType){
        Session session = sessionFactory.getCurrentSession();
        Application application = new Application();
        application.setEmployee(employee);
        application.setCreateDate(createDate);
        application.setStatus(status);
        application.setComments(comments);
        application.setApplicationType(applicationType);
        return (Integer) session.save(application);
    }

    public Integer addNewDocumentApplication(Employee employee, String createDate, String status,
                                             String comments, String applicationType) {
        Application application = new Application();
        application.setEmployee(employee);
        application.setCreateDate(createDate);
        application.setStatus(status);
        application.setComments(comments);
        application.setApplicationType(applicationType);
        return (Integer) sessionFactory.getCurrentSession().save(application);
    }

    public Application getApplicationByEmployeeAndType(Employee employee, String applicationType) {
        String statement = "FROM Application WHERE employee = :employee AND applicationType = :applicationType";
        Query query = sessionFactory.getCurrentSession().createQuery(statement);
        query.setParameter("employee", employee);
        query.setParameter("applicationType", applicationType);
        List<Application> resList = query.getResultList();
        return resList.size() == 0? null : resList.get(0);
    }

    public void updateApplicationByEmployeeAndType(Employee employee, String applicationType, String status, String comments, String modificationDate) {
        Application application = getApplicationByEmployeeAndType(employee, applicationType);
        if (application != null) {
            application.setComments(comments);
            application.setStatus(status);
            application.setModificationDate(modificationDate);
            sessionFactory.getCurrentSession().update(application);
        }
    }

    public Application getOnboardingApplicationForEmployee(Employee employee){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from Application where employee = :employee and applicationType = 'Onboarding'";
        Query query = session.createQuery(statement);
        query.setParameter("employee", employee);
        List<Application> applications = query.getResultList();
        if (applications.isEmpty()){
            return null;
        }
        return applications.get(0);
    }

    public List<Application> getAllApplications(){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from Application";
        Query query = session.createQuery(statement);
        return query.getResultList();
    }

    public Application getApplicationById(Integer aid){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Application.class, aid);
    }

    public void approveApplication(Integer aid){
        Session session = sessionFactory.getCurrentSession();
        Application application = session.get(Application.class, aid);
        application.setStatus("approved");
        application.setModificationDate(DatetimeUtil.getCurrentDateTime());
    }

    public void rejectApplication(Integer aid){
        Session session = sessionFactory.getCurrentSession();
        Application application = session.get(Application.class, aid);
        application.setModificationDate(DatetimeUtil.getCurrentDateTime());
        application.setStatus("rejected");
    }

    public void commentApplication(Integer aid, String comment){
        Session session = sessionFactory.getCurrentSession();
        Application application = session.get(Application.class, aid);
        application.setComments(comment);
        application.setStatus("action needed");
        application.setModificationDate(DatetimeUtil.getCurrentDateTime());
    }

    public void updateApplication(Integer aid){
        Session session = sessionFactory.getCurrentSession();
        Application application = session.get(Application.class, aid);
        application.setModificationDate(DatetimeUtil.getCurrentDateTime());
        application.setStatus("pending");
        application.setComments("Pending to be reviewed by HR");
    }
}
