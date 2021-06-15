package com.beaconfire.HRServer.service;

import com.beaconfire.HRServer.dao.ApplicationDAO;
import com.beaconfire.HRServer.dao.DocumentDAO;
import com.beaconfire.HRServer.dao.EmployeeDAO;
import com.beaconfire.HRServer.dao.UserDAO;
import com.beaconfire.HRServer.domain.Application;
import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.PersonalDocument;
import com.beaconfire.HRServer.util.DatetimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.Document;
import java.util.List;

@Service
public class ApplicationService {
    private EmployeeDAO employeeDAO;
    private ApplicationDAO applicationDAO;
    private DocumentDAO documentDAO;

    private UserDAO userDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setApplicationDAO(ApplicationDAO applicationDAO) {
        this.applicationDAO = applicationDAO;
    }

    @Autowired
    public void setDocumentDAO(DocumentDAO documentDAO){this.documentDAO = documentDAO;}

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Transactional
    public Integer addNewOnboardingApplication(Integer employeeId){
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        return applicationDAO.addNewOnboardingApplication(employee, DatetimeUtil.getCurrentDateTime(), "pending",
                "Pending to be reviewed by HR", "Onboarding");
    }

    @Transactional

    public Application getDocumentApplicationByEmployeeId(int eid) {
        Employee employee = employeeDAO.getEmployeeById(eid);
        return applicationDAO.getApplicationByEmployeeAndType(employee, "Document");
    }

    @Transactional
    public void updateDocumentApplicationByUserId(int uid, String status, String comments) {
        Employee employee = employeeDAO.getEmployeeByUser(userDAO.getUserById(uid)).get(0);
        applicationDAO.updateApplicationByEmployeeAndType(employee, "Document", status, comments, DatetimeUtil.getCurrentDateTime());
    }

    @Transactional
    public Application getOnboardingApplication(String email){
        Employee employee = employeeDAO.getEmployeeByEmail(email);
        if (employee == null){
            return null;
        }
        Application application = applicationDAO.getOnboardingApplicationForEmployee(employee);
        if (application == null){
            return null;
        }
        return application;
    }

    @Transactional
    public Employee getEmployeeByID(Integer eid){
        return employeeDAO.getEmployeeById(eid);
    }

    @Transactional
    public List<PersonalDocument> getPersonalDocuments(Employee employee){
        return documentDAO.getPersonalDocumentByEmployeeId(employee);
    }

    @Transactional
    public Integer addNewDocumentApplication(Integer employeeId){
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        return applicationDAO.addNewOnboardingApplication(employee, DatetimeUtil.getCurrentDateTime(),
                "OPT Receipt|pending", "Pending to be reviewed by HR", "Document");
    }

    @Transactional
    public List<Application> getAllApplications(){
        return applicationDAO.getAllApplications();
    }

    @Transactional
    public Application getApplicationById(Integer aid){
        return applicationDAO.getApplicationById(aid);
    }

    @Transactional
    public void approveApplication(Integer aid){
        applicationDAO.approveApplication(aid);
    }

    @Transactional
    public void rejectApplication(Integer aid){
        applicationDAO.rejectApplication(aid);
    }

    @Transactional
    public void commentApplicaiton(Integer aid, String comment){
        applicationDAO.commentApplication(aid, comment);
    }

    @Transactional
    public void updateApplication(Integer aid){
        applicationDAO.updateApplication(aid);
    }
}
