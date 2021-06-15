package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.domain.Application;
import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.response.ApplicationInfoResponse;
import com.beaconfire.HRServer.response.EmployeeHomePageResponse;
import com.beaconfire.HRServer.service.ApplicationService;
import com.beaconfire.HRServer.service.EmployeeHomePageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeHomePageControllerTest {

    private EmployeeHomePageService mockEmployeeHomePageService;
    private EmployeeHomePageController mockEmployeeHomePageController;



    @BeforeEach
    public void setup(){
        mockEmployeeHomePageService = mock(EmployeeHomePageService.class);
        mockEmployeeHomePageController = new EmployeeHomePageController();
        mockEmployeeHomePageController.setEmployeeHomePageService(mockEmployeeHomePageService);
        when(mockEmployeeHomePageService.getEmployeeInfo(1)).thenReturn(new Employee());
    }

    @Test
    public void testGetEmployeeHomePage(){
        int eid = 1;
        EmployeeHomePageResponse employeeHomePageResponse = mockEmployeeHomePageController.getEmployeeHomePageInfo("1");
        assertNotNull(employeeHomePageResponse);
    }
}
