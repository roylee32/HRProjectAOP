package com.beaconfire.HRServer.controller;


import com.beaconfire.HRServer.response.HrEmailResponse;
import com.beaconfire.HRServer.request.HrEmailRequest;
import com.beaconfire.HRServer.service.HrRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HrEmailController {

    HrRegistrationService hrRegistrationService;

    @Autowired
    public void setHrRegistrationService(HrRegistrationService hrRegistrationService) {
        this.hrRegistrationService = hrRegistrationService;
    }

    @PostMapping(value = "/hr/api/hrSendEmail", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public HrEmailResponse hrSendEmailAndInsert(@RequestBody HrEmailRequest request){
        HrEmailResponse response = new HrEmailResponse();
        if(request.getEmail() == null || request.getEmail() == ""){
            response.setErrorMessage("Email not entered");
            return response;
        }
        else {
            hrRegistrationService.doInsertAndSendEmail(request.getEmail());
        }
        return response;
    }
}
