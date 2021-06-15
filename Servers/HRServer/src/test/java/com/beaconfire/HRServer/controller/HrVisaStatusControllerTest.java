package com.beaconfire.HRServer.controller;
import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.request.HrVisaStatusRequest;
import com.beaconfire.HRServer.response.HrVisaStatusResponse;
import com.beaconfire.HRServer.service.EmployeeService;
import com.beaconfire.HRServer.service.HrRegistrationService;
import com.beaconfire.HRServer.service.HrVisaStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HrVisaStatusControllerTest {
    private HrVisaStatusService mockHrVisaStatusService;
    private HrRegistrationService mockHrRegistrationService;
    private EmployeeService mockEmployeeService;
    private HrVisaStatusController mockHrVisaStatusController;

    @BeforeEach
    public void setup(){
        mockHrVisaStatusService = mock(HrVisaStatusService.class);
        mockHrRegistrationService = mock(HrRegistrationService.class);
        mockEmployeeService = mock(EmployeeService.class);
        mockHrVisaStatusController = new HrVisaStatusController();
        mockHrVisaStatusController.setHrVisaStatusService(mockHrVisaStatusService);
        mockHrVisaStatusController.setHrRegistrationService(mockHrRegistrationService);
        mockHrVisaStatusController.setEmployeeService(mockEmployeeService);
        when(mockEmployeeService.getEmployeeByUid(1)).thenReturn(new Employee());
    }

    @Test
    public void testGetEmergencyContactInfo(){
        String uid = "1";
        HrVisaStatusResponse response = mockHrVisaStatusController.getEmergencyContactInfo(uid);
        assertNotNull(response);
    }

    @Test
    public void testPostEmergencyContactInfo(){
        HrVisaStatusRequest request = new HrVisaStatusRequest();
        request.setEid("1");
        request.setFirstName("Test");
        request.setLastName("test");
        HrVisaStatusResponse response = mockHrVisaStatusController.postEmergencyContactInfo(request);
        assertNotNull(response);
    }

    @Test
    public void testSendEmailToEmployee(){
        HrVisaStatusRequest request = new HrVisaStatusRequest();
        request.setEmail("test@test.com");
        request.setFullName("test test");
        request.setNextSteps("test steps");
        HrVisaStatusResponse response = mockHrVisaStatusController.sendEmailToEmployee(request);
        assertNotNull(response);
    }
}
