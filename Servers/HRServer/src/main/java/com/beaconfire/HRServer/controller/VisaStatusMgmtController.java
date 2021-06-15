package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.PersonalDocument;
import com.beaconfire.HRServer.domain.VisaStatus;
import com.beaconfire.HRServer.request.VisaStatusMgmtRequest;
import com.beaconfire.HRServer.response.VisaStatusMgmtResponse;
import com.beaconfire.HRServer.service.ApplicationService;
import com.beaconfire.HRServer.service.DocumentService;
import com.beaconfire.HRServer.service.EmployeeService;
import com.beaconfire.HRServer.service.VisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VisaStatusMgmtController {

    private VisaStatusService visaStatusService;
    private DocumentService documentService;
    private ApplicationService applicationService;

    @Autowired
    public void setVisaStatusService(VisaStatusService visaStatusService) {
        this.visaStatusService = visaStatusService;
    }

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Autowired
    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @GetMapping(value = "/hr/api/visaStatusMgmt")
    public VisaStatusMgmtResponse getVisaStatusInfoByUserId(@RequestParam String eid) {
        VisaStatusMgmtResponse response = new VisaStatusMgmtResponse();
        response.setVisaStatus(visaStatusService.getVisaStatusByEmployeeId(Integer.parseInt(eid)));
        response.setApplication(applicationService.getDocumentApplicationByEmployeeId(Integer.parseInt(eid)));
        response.setPersonalDocuments(documentService.filterVisaStatusDocuments(documentService.getAllPersonalDocumentsForEmployee(eid)));
        documentService.sortPersonalDocuments(response.getPersonalDocuments());
        return response;
    }

    @PostMapping(value = "hr/api/visaStatusMgmt", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public VisaStatusMgmtResponse updateVisaStatusInfo(@RequestBody VisaStatusMgmtRequest request) {
        VisaStatusMgmtResponse response = new VisaStatusMgmtResponse();
        applicationService.updateDocumentApplicationByUserId(
                Integer.parseInt(request.getUid()),
                request.getApplication().getStatus(),
                request.getApplication().getComments());
        return response;
    }
}
