package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.PersonalDocument;
import com.beaconfire.HRServer.request.ApplicationDetailDocumentsRequest;
import com.beaconfire.HRServer.response.ApplicationDetailDocumentsResponse;
import com.beaconfire.HRServer.service.ApplicationService;
import com.beaconfire.HRServer.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApplicationDocumentController {
    ApplicationService applicationService;
    S3Service s3Service;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    @Autowired
    public void setS3Service(S3Service s3Service){
        this.s3Service = s3Service;
    }

    @GetMapping(value = "/hr/api/getDocuments/{eid}")
    public ApplicationDetailDocumentsResponse getApplicationDocumentDetails(@PathVariable Integer eid){
//        System.out.println(eid);
        logger.info("/hr/api/getDocuments/" + String.valueOf(eid));
        ApplicationDetailDocumentsResponse response = new ApplicationDetailDocumentsResponse();
        Employee employee = applicationService.getEmployeeByID(eid);
        List<PersonalDocument> pDocs = new ArrayList<>();
        pDocs = applicationService.getPersonalDocuments(employee);
        response.setPDocs(pDocs);
        return response;
    }


    @PostMapping(value = "/hr/api/getDocuments/editAndReview", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ApplicationDetailDocumentsResponse getApplicationDocumentsDetails(@RequestBody ApplicationDetailDocumentsRequest request){
        ApplicationDetailDocumentsResponse response = new ApplicationDetailDocumentsResponse();
        String newPath = s3Service.getSignedUrlByPath(request.getPath());
        response.setSignedPath(newPath);
        return response;
    }



}
