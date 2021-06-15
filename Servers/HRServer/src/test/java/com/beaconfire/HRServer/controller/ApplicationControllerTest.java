package com.beaconfire.HRServer.controller;


import com.beaconfire.HRServer.domain.Application;

import com.beaconfire.HRServer.request.ApplicationApproveRejectRequest;
import com.beaconfire.HRServer.request.ApplicationCommentRequest;
import com.beaconfire.HRServer.response.AllApplicationInfoResponse;
import com.beaconfire.HRServer.response.ApplicationDetailResponse;
import com.beaconfire.HRServer.response.ApplicationInfoResponse;
import com.beaconfire.HRServer.response.MessageResponse;
import com.beaconfire.HRServer.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.plugin2.message.Message;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ApplicationControllerTest {
    private ApplicationService mockApplicationService;
    private ApplicationController applicationController;
    private AddressService mockAddressService;
    private VisaStatusService mockVisaStatusService;
    private ContactService mockContactService;
    private S3Service mockS3Service;

    @BeforeEach
    public void setup(){
        mockApplicationService = mock(ApplicationService.class);
        applicationController = new ApplicationController();
        applicationController.setApplicationService(mockApplicationService);
        applicationController.setAddressService(mockAddressService);
        applicationController.setVisaStatusService(mockVisaStatusService);
        applicationController.setContactService(mockContactService);
        applicationController.setS3Service(mockS3Service);
        when(mockApplicationService.getOnboardingApplication("atlantahoon@gmail.com")).thenReturn(new Application());
    }

    @Test
    public void testGetOnboardingApplication(){
        String email = "atlantahoon@gmail.com";
        ApplicationInfoResponse applicationInfoResponse = applicationController.getSimpleApplicationInfo(email);
        assertNotNull(applicationInfoResponse);
    }

    @Test
    public void testGetAllApplications(){
        AllApplicationInfoResponse response = applicationController.getAllApplications();
        assertNotNull(response);
    }

    @Test
    public void testApproveApplications(){
        ApplicationApproveRejectRequest request = new ApplicationApproveRejectRequest();
        request.setAid(1);
        MessageResponse response = applicationController.approveApplication(request);
        assertNotNull(response);
    }

    @Test
    public void testRejectApplication(){
        ApplicationApproveRejectRequest request = new ApplicationApproveRejectRequest();
        request.setAid(1);
        MessageResponse response = applicationController.rejectApplication(request);
        assertNotNull(response);
    }

    @Test
    public void testCommentApplication(){
        ApplicationCommentRequest request = new ApplicationCommentRequest();
        request.setAid(1);
        request.setComment("This is test comment");
        MessageResponse response = applicationController.commentApplication(request);
        assertNotNull(response);
    }


    /*
    @BeforeEach
    public void setup2(){
        mockApplicationService = mock(ApplicationService.class);
        addressService = mock(AddressService.class);
        visaStatusService = mock(VisaStatusService.class);
        contactService = mock(ContactService.class);
        s3Service = mock(S3Service.class);
        applicationController = new ApplicationController();
        applicationController.setApplicationService(mockApplicationService);
        when(mockApplicationService.getApplicationById(1)).thenReturn(new Application());
        when(addressService.)
    }

    @Test
    public void testGet (){
        int aid = 1;
        ApplicationDetailResponse applicationDetailResponse = applicationController.getApplicationDetail(aid);
        assertNotNull(applicationDetailResponse);
    }

     */
}
