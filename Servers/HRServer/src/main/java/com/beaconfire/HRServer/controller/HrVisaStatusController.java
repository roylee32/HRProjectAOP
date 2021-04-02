package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.dao.EmployeeDAO;
import com.beaconfire.HRServer.domain.Contact;
import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.PersonalDocument;
import com.beaconfire.HRServer.domain.VisaStatus;
import com.beaconfire.HRServer.request.HrVisaStatusRequest;
import com.beaconfire.HRServer.request.NameSectionRequest;
import com.beaconfire.HRServer.response.HrVisaStatusResponse;
import com.beaconfire.HRServer.response.NameSectionResponse;
import com.beaconfire.HRServer.service.EmployeeService;
import com.beaconfire.HRServer.service.HrRegistrationService;
import com.beaconfire.HRServer.service.HrVisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HrVisaStatusController {

    HrVisaStatusService hrVisaStatusService;
    HrRegistrationService hrRegistrationService;
    EmployeeService employeeService;

    @Autowired
    public void setHrVisaStatusService(HrVisaStatusService hrVisaStatusService){
        this.hrVisaStatusService = hrVisaStatusService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @Autowired
    public void setHrRegistrationService(HrRegistrationService hrRegistrationService){
        this.hrRegistrationService = hrRegistrationService;
    }

    @GetMapping(value="/hr/api/hrVisaStatusManagement")
    public HrVisaStatusResponse getEmergencyContactInfo(@RequestParam String uid){
        HrVisaStatusResponse response = new HrVisaStatusResponse();
        Employee e = employeeService.getEmployeeByUid(Integer.parseInt(uid));
        List<VisaStatus> visaList = hrVisaStatusService.getEveryVisaStatus();
        List<Employee> employeeList = hrVisaStatusService.getEveryEmployeeMatchingVisaStatus(visaList);
        response.setEmployee(e);
        response.setVisas(visaList);
        response.setEmployees(employeeList);
        return response;
    }

    @PostMapping(value="/hr/api/hrVisaStatusManagements", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public HrVisaStatusResponse postEmergencyContactInfo(@RequestBody HrVisaStatusRequest request){
        HrVisaStatusResponse response = new HrVisaStatusResponse();
        Employee employee = hrVisaStatusService.getSingleEmployeeById(Integer.parseInt(request.getEid()));
        List<PersonalDocument> pList = hrVisaStatusService.getPersonalDocumentByEmployee(employee);
        response.setPDocs(pList);
        response.setFullName(request.getFirstName() +' '+ request.getLastName());
        response.setNextSteps(hrVisaStatusService.getNextStep(pList));
        return response;
    }

    @PostMapping(value="/hr/api/hrVisaStatusManagement/sendEmail", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public HrVisaStatusResponse sendEmailToEmployee(@RequestBody HrVisaStatusRequest request){
        HrVisaStatusResponse response = new HrVisaStatusResponse();
        if(request.getNextSteps().equals("No Next Steps")){
            response.setErrorMessage("Cannot send email. No next steps");
            return response;
        }
        else{
            hrRegistrationService.sendActionEmail(request.getEmail(), "BeaconFire Solutions: Next Steps", request.getFullName(), request.getNextSteps());
        }
        return response;
    }
}
