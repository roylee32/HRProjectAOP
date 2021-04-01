package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.domain.Application;
import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.request.ApplicationApproveRejectRequest;
import com.beaconfire.HRServer.request.ApplicationCommentRequest;
import com.beaconfire.HRServer.response.AllApplicationInfoResponse;
import com.beaconfire.HRServer.response.ApplicationDetailResponse;
import com.beaconfire.HRServer.response.ApplicationInfoResponse;
import com.beaconfire.HRServer.response.MessageResponse;
import com.beaconfire.HRServer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {
    private ApplicationService applicationService;
    private AddressService addressService;
    private VisaStatusService visaStatusService;
    private ContactService contactService;
    private S3Service s3Service;

    @Autowired
    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setVisaStatusService(VisaStatusService visaStatusService) {
        this.visaStatusService = visaStatusService;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setS3Service(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping(value = "/hr/api/getAllApplications")
    public AllApplicationInfoResponse getAllApplications(){
        AllApplicationInfoResponse response = new AllApplicationInfoResponse();
        response.setApplications(applicationService.getAllApplications());
        return response;
    }

    @GetMapping(value = "/hr/api/getApplicationDetail/{aid}")
    public ApplicationDetailResponse getApplicationDetail(@PathVariable Integer aid){
        ApplicationDetailResponse response = new ApplicationDetailResponse();
        Application application = applicationService.getApplicationById(aid);
        response.setApplication(application);
        Employee employee = application.getEmployee();
        response.setAddresses(addressService.getAddressByEmployee(employee));
        response.setVisaStatuses(visaStatusService.getVisaStatusByEmployee(employee));
        response.setReferenceContact(contactService.getReferenceContactByEmployee(employee));
        response.setEmergenceContact(contactService.getEmergenceContactByEmployee(employee));
        response.setAvatarURL(s3Service.getSignedUrlByPath(employee.getAvartar()));
        return response;
    }

    @PostMapping(value = "/hr/api/approveApplication", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public MessageResponse approveApplication(@RequestBody ApplicationApproveRejectRequest request){
        MessageResponse response = new MessageResponse();
        applicationService.approveApplication(request.getAid());
        response.setMessage("Application Approved.");
        return response;
    }

    @PostMapping(value = "/hr/api/rejectApplication", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public MessageResponse rejectApplication(@RequestBody ApplicationApproveRejectRequest request){
        MessageResponse response = new MessageResponse();
        applicationService.rejectApplication(request.getAid());
        response.setMessage("Application Rejected.");
        return response;
    }

    @PostMapping(value = "/hr/api/commentApplication", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public MessageResponse commentApplication(@RequestBody ApplicationCommentRequest request){
        MessageResponse response = new MessageResponse();
        applicationService.commentApplicaiton(request.getAid(), request.getComment());
        response.setMessage("Comment Submitted.");
        return response;
    }

    @GetMapping(value = "/hr/api/getSimpleApplicationInfo/{email}")
    public ApplicationInfoResponse getSimpleApplicationInfo(@PathVariable String email){
        ApplicationInfoResponse response = new ApplicationInfoResponse();
        Application application = applicationService.getOnboardingApplication(email);
        if (application != null){
            response.setApplication(application);
        }
        return response;
    }

}
