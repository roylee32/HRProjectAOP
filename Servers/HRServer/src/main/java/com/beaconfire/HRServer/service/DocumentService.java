package com.beaconfire.HRServer.service;

import com.beaconfire.HRServer.dao.DocumentDAO;
import com.beaconfire.HRServer.dao.EmployeeDAO;
import com.beaconfire.HRServer.domain.DigitalDocument;
import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.PersonalDocument;
import com.beaconfire.HRServer.util.DatetimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DocumentService {
    private DocumentDAO documentDAO;
    private EmployeeDAO employeeDAO;

    @Autowired
    public void setDocumentDAO(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Transactional
    public List<DigitalDocument> getAllDigitalDocuments(){
        return documentDAO.getAllDigitalDocuments();
    }

    @Transactional
    public List<PersonalDocument> getAllPersonalDocumentsForEmployee(String employeeId){
        Employee employee = employeeDAO.getEmployeeById(Integer.valueOf(employeeId));
        return documentDAO.getPersonalDocumentByEmployeeId(employee);
    }

    public void sortPersonalDocuments(List<PersonalDocument> unsorted) {
        Collections.sort(unsorted, (pd1, pd2) -> DatetimeUtil.compareTimes(pd2.getCreateDate(), pd1.getCreateDate()));
    }

    public List<PersonalDocument> filterVisaStatusDocuments(List<PersonalDocument> unfiltered) {
        List<PersonalDocument> res = new ArrayList<>();
        for (PersonalDocument doc : unfiltered) {
            if (doc.getTitle().equals("EAD.pdf") || doc.getTitle().equals("OPT.pdf") || doc.getTitle().equals("I-983.pdf") ||
                    doc.getTitle().equals("I-20.pdf") || doc.getTitle().equals("OPTSTEM.pdf") || doc.getTitle().equals("EADSTEM.pdf") || doc.getTitle().equals("Application_1.pdf")) {
                res.add(doc);
            }
        }
        return res;
    }

    @Transactional
    public Integer uploadPersonalDocumentForEmployee(String employeeId, String path,
                                                     String title, String comment){
        Employee employee = employeeDAO.getEmployeeById(Integer.valueOf(employeeId));
        return documentDAO.uploadPersonalDocumentForEmployee(employee, path, title,
                comment, DatetimeUtil.getCurrentDateTime());
    }

}
