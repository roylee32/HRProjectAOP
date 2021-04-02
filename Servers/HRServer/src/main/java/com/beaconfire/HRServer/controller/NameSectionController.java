package com.beaconfire.HRServer.controller;


import com.beaconfire.HRServer.domain.Contact;
import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.VisaStatus;
import com.beaconfire.HRServer.request.NameSectionRequest;
import com.beaconfire.HRServer.response.NameSectionResponse;
import com.beaconfire.HRServer.service.NameSectionService;
import com.beaconfire.HRServer.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NameSectionController {

    NameSectionService nameSectionService;
    S3Service s3Service;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setNameSectionService(NameSectionService nameSectionService){
        this.nameSectionService = nameSectionService;
    }

    @Autowired
    public void setS3Service(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping(value="/hr/api/userProfilePage/userNameInfo")
    public NameSectionResponse getInitialFormValue(@RequestParam String eid){

        NameSectionResponse response = new NameSectionResponse();
        Employee employee = nameSectionService.getEmployeeInfoByEID(Integer.parseInt(eid));
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setMiddleName(employee.getMiddleName());
        response.setFullName(employee.getFirstName() + employee.getLastName());
        response.setBirthday(employee.getDOB());
        response.setGender(employee.getGender());
        response.setSSN(employee.getSSN());
        String avartarPath = employee.getAvartar();
        response.setAvartarUrl(avartarPath == null? null : s3Service.getSignedUrlByPath(avartarPath));
        return response;
    }

    @PostMapping(value="/hr/api/userProfilePage/userNameInfo", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public NameSectionResponse getInitialFormValue(@RequestBody NameSectionRequest request){
        NameSectionResponse response = new NameSectionResponse();
//        System.out.println(request.getEid());
//        System.out.println(request.getFirstName());
//        System.out.println(request.getLastName());
//        System.out.println(request.getBirthday());
//        System.out.println(request.getGender());
        logger.info("Eid: " + request.getEid());
        logger.info("First name: " + request.getFirstName());
        logger.info("Last name: " + request.getLastName());
        logger.info("Birthday: " + request.getBirthday());
        logger.info("Gender: " + request.getGender());

        nameSectionService.updateEmployeeNameSectionInfo(request.getFirstName(), request.getMiddleName(), request.getLastName(), request.getBirthday(), request.getGender(), Integer.parseInt(request.getEid()));
        return response;
    }

    @GetMapping(value="/hr/api/userProfilePage/contactInfo")
    public NameSectionResponse getContactInfoValue(@RequestParam String eid){

        NameSectionResponse response = new NameSectionResponse();
        Employee employee = nameSectionService.getEmployeeInfoByEID(Integer.parseInt(eid));
        response.setPersonalEmail(employee.getEmail());
        response.setCellPhone(employee.getCellPhone());
        response.setWorkPhone(employee.getAlternatePhone());
        return response;
    }

    @PostMapping(value="/hr/api/userProfilePage/contactInfo", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public NameSectionResponse getContactInfoValue(@RequestBody NameSectionRequest request){
        NameSectionResponse response = new NameSectionResponse();
        nameSectionService.updateEmployeeContactInfoSectionInfo(request.getPersonalEmail(), request.getCellPhone(), request.getWorkPhone(), Integer.parseInt(request.getEid()));
        return response;
    }

    @GetMapping(value="/hr/api/userProfilePage/employmentInfo")
    public NameSectionResponse getEmploymentInfo(@RequestParam String eid){

        NameSectionResponse response = new NameSectionResponse();
        Employee employee = nameSectionService.getEmployeeInfoByEID(Integer.parseInt(eid));
        VisaStatus visaStatus = nameSectionService.getVisaStatusByEmployeeID(Integer.parseInt(eid));
        if(visaStatus != null){
            response.setWorkAuth(visaStatus.getVisaType());
            response.setWorkAuthStartDate(visaStatus.getVisaStartDate());
            response.setWorkAuthEndDate(visaStatus.getVisaEndDate());
        }
        response.setEmploymentStartDate(employee.getStartDate());
        response.setEmploymentEndDate(employee.getEndDate());
        response.setTitle(employee.getTitle());
        return response;
    }

    @PostMapping(value="/hr/api/userProfilePage/employmentInfo", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public NameSectionResponse getEmploymentInfo(@RequestBody NameSectionRequest request){
        NameSectionResponse response = new NameSectionResponse();
        nameSectionService.updateEmployeeEmploymentInfoSection(request.getWorkAuth(), request.getWorkAuthStartDate(), request.getWorkAuthEndDate(), request.getEmploymentStartDate(), request.getEmploymentEndDate(), request.getTitle(), Integer.parseInt(request.getEid()));
        return response;
    }


    @GetMapping(value="/hr/api/userProfilePage/emergencyContactInfo")
    public NameSectionResponse getEmergencyContactInfo(@RequestParam String eid){

        NameSectionResponse response = new NameSectionResponse();
        Employee employee = nameSectionService.getEmployeeInfoByEID(Integer.parseInt(eid));
        List<Contact> list = nameSectionService.getContactsFromUid(employee);
        if(!list.isEmpty()){
            response.setContacts(list);
        }
        return response;
    }


}
