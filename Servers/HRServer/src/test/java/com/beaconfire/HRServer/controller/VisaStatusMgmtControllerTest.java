package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.domain.Application;
import com.beaconfire.HRServer.domain.VisaStatus;
import com.beaconfire.HRServer.request.RegistrationRequest;
import com.beaconfire.HRServer.request.VisaStatusMgmtRequest;
import com.beaconfire.HRServer.response.RegistrationResponse;
import com.beaconfire.HRServer.response.VisaStatusMgmtResponse;
import com.beaconfire.HRServer.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VisaStatusMgmtControllerTest {
    private VisaStatusService mockVisaStatusService;
    private DocumentService mockDocumentService;
    private ApplicationService mockApplicationService;
    private VisaStatusMgmtController mockVisaStatusMgmtController;


    @BeforeEach
    public void setup(){
        mockVisaStatusService = mock(VisaStatusService.class);
        mockDocumentService = mock(DocumentService.class);
        mockApplicationService = mock(ApplicationService.class);
        mockVisaStatusMgmtController = new VisaStatusMgmtController();
        mockVisaStatusMgmtController.setVisaStatusService(mockVisaStatusService);
        mockVisaStatusMgmtController.setDocumentService(mockDocumentService);
        mockVisaStatusMgmtController.setApplicationService(mockApplicationService);
        when(mockVisaStatusService.getVisaStatusByEmployeeId(1)).thenReturn(new VisaStatus());
    }

    @Test
    public void testGetVisaStatusByUserId(){
        String eid = "1";
        VisaStatusMgmtResponse response = mockVisaStatusMgmtController.getVisaStatusInfoByUserId(eid);
        assertNotNull(response);
    }

    @Test
    public void testUpdateVisaStatusInfo(){
        VisaStatusMgmtRequest request = new VisaStatusMgmtRequest();
        request.setApplication(new Application());
        request.setUid("1");
        VisaStatusMgmtResponse response = mockVisaStatusMgmtController.updateVisaStatusInfo(request);
        assertNotNull(response);
    }
}
