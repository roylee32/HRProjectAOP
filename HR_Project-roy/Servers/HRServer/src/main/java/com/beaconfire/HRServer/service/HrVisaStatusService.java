package com.beaconfire.HRServer.service;

import com.beaconfire.HRServer.dao.DocumentDAO;
import com.beaconfire.HRServer.dao.EmployeeDAO;
import com.beaconfire.HRServer.dao.VisaStatusDAO;
import com.beaconfire.HRServer.domain.Contact;
import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.PersonalDocument;
import com.beaconfire.HRServer.domain.VisaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HrVisaStatusService {
    VisaStatusDAO visaStatusDAO;
    EmployeeDAO employeeDAO;
    DocumentDAO documentDAO;

    @Autowired
    public void setVisaStatusDAO(VisaStatusDAO visaStatusDAO) {this.visaStatusDAO = visaStatusDAO;}

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {this.employeeDAO = employeeDAO;}

    @Autowired
    public void setDocumentDAO(DocumentDAO documentDAO) {this.documentDAO = documentDAO;}

    @Transactional
    public List<VisaStatus> getEveryVisaStatus(){
        List<VisaStatus> allVisaStatus = new ArrayList<>();
        allVisaStatus = visaStatusDAO.getAllVisaStatus();
        return allVisaStatus;
    }
    @Transactional
    public List<Employee> getEveryEmployeeMatchingVisaStatus(List<VisaStatus> visaList){
        List<Employee> allEmployeeList = new ArrayList<>();
        for (int i = 0; i < visaList.size(); i++){
            Employee e = visaList.get(i).getEmployee();
            allEmployeeList.add(e);
        }
        return allEmployeeList;
    }

    @Transactional
    public Employee getSingleEmployeeById(Integer eid){
        return employeeDAO.getEmployeeById(eid);
    }

    @Transactional
    public List<PersonalDocument> getPersonalDocumentByEmployee(Employee e){
        List<PersonalDocument> pDocs = new ArrayList<>();
        List<PersonalDocument> newpDocs = new ArrayList<>();
        pDocs = documentDAO.getPersonalDocumentByEmployeeId(e);
        for (int i = 0; i < pDocs.size(); i++){
            System.out.println(pDocs.get(i).getTitle());
            if(pDocs.get(i).getTitle().equals("EAD.pdf") || pDocs.get(i).getTitle().equals("OPT.pdf") || pDocs.get(i).getTitle().equals("I-983.pdf")
            || pDocs.get(i).getTitle().equals("I-20.pdf") || pDocs.get(i).getTitle().equals("OPTSTEM.pdf") || pDocs.get(i).getTitle().equals("EADSTEM.pdf")){
                newpDocs.add(pDocs.get(i));
            }
        }
        return newpDocs;
    }

    public String getNextStep(List<PersonalDocument> list){
        List<String> plist = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            plist.add(list.get(i).getTitle());
        }
        System.out.println(plist);
        if (!plist.contains("OPT.pdf")) {
            return "Next Step: Upload OPT";
        } else if (!plist.contains("EAD.pdf")) {
            return "Next Step: Upload EAD";
        } else if (!plist.contains("I-983.pdf")) {
            return "Next Step: Upload I-983";
        } else if (!plist.contains("I-20.pdf")) {
            return "Next Step: Upload I-20";
        } else if (!plist.contains("OPTSTEM.pdf")) {
            return "Next Step: Upload OPTSTEM";
        } else if (!plist.contains("EADSTEM.pdf")) {
            return "Next Step: Upload EADSTEM";
        } else {
            return "No Next Steps";
        }
    }

}
