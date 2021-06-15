package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.service.HrRegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class HrEmailControllerTest {
    private HrRegistrationService mockHrRegistrationService;
    private HrEmailController mockHrEmailController;
/*
    @BeforeEach
    public void setup(){
        mockHrRegistrationService = mock(HrRegistrationService.class);
        mockHrEmailController = new HrEmailController();
        mockHrEmailController.setHrRegistrationService(mockHrRegistrationService);
        doNothing().when(mockHrRegistrationService.doInsertAndSendEmail("test@test.com"));
    }

    @Test
    public void testGetHouseInfo(){
        String eid = "1";
        HouseInfoResponse houseInfoResponse = mockHousingController.getHouseInfo(eid);
        assertNotNull(houseInfoResponse);
    }
    */

}
