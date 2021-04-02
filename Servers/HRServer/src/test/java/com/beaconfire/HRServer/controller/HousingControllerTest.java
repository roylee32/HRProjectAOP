package com.beaconfire.HRServer.controller;


import com.beaconfire.HRServer.ExceptionOliverLi.HouseNotFoundException;
import com.beaconfire.HRServer.domain.Contact;
import com.beaconfire.HRServer.domain.House;
import com.beaconfire.HRServer.request.AddNewHouseRequest;
import com.beaconfire.HRServer.request.FacilityReportRequest;
import com.beaconfire.HRServer.request.ReportCommentRequest;
import com.beaconfire.HRServer.request.UpdateReportCommentRequest;
import com.beaconfire.HRServer.response.*;
import com.beaconfire.HRServer.service.ContactService;
import com.beaconfire.HRServer.service.HousingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HousingControllerTest {

    private HousingService mockHousingService;
    private HousingController mockHousingController;
    private ContactService mockContactService;

    @BeforeEach
    public void setup(){
        mockHousingService = mock(HousingService.class);
        mockHousingController = new HousingController();
        mockHousingController.setHousingService(mockHousingService);
        mockHousingController.setContactService(mockContactService);
        when(mockHousingService.getHouseInfoByEid("1")).thenReturn(new House());
    }

    @Test
    public void testGetHouseInfo(){
        String eid = "1";
        HouseInfoResponse houseInfoResponse = mockHousingController.getHouseInfo(eid);
        assertNotNull(houseInfoResponse);
    }

    @Test
    public void testCreateNewFacilityReport(){
        FacilityReportRequest request = new FacilityReportRequest();
        request.setHid(1);
        request.setEid("1");
        request.setDescription("Test Desc");
        request.setTitle("Title TEST");
        FacilityReportResponse response = mockHousingController.createNewFacilityReport(request);
        assertNotNull(response);
    }

    @Test
    public void testGetReportInfo(){
        String frid = "1";
        ReportInfoResponse response = mockHousingController.getReportInfo(frid);
        assertNotNull(response);
    }

    @Test
    public void testCreateNewComment(){
        ReportCommentRequest request = new ReportCommentRequest();
        request.setEid("1");
        request.setComment("Test Comment");
        request.setReportId("1");
        ReportCommentResponse response = mockHousingController.createNewComment(request);
        assertNotNull(response);
    }

    @Test
    public void testUpdateComment(){
        UpdateReportCommentRequest request = new UpdateReportCommentRequest();
        request.setComment("Test Update Comment");
        request.setFrdid(1);
        ReportCommentResponse response = mockHousingController.updateComment(request);
        assertNotNull(request);
    }

    @Test
    public void testAllHouseInfo(){
        AllHousesInfoResponse response = mockHousingController.getAllHouseInfo();
        assertNotNull(response);
    }
/*
    @Test
    public void testAddNewHouse(){
        AddNewHouseRequest request = new AddNewHouseRequest();
        request.setHouseAddress("Test Address");
        request.setEmail("test@email.com");
        request.setFirstName("Test");
        request.setLandlordAddress("Test LL address");
        request.setLastName("Test");
        request.setMiddleName("T");
        request.setPhoneNumber("1800Test");
        request.setSize(1);
        ReportCommentResponse response = mockHousingController.addNewHouse(request);
        assertNotNull(response);
    }
*/
    @Test
    public void testGetHouseInfo2() throws HouseNotFoundException {
        int hid = 1;
        HrHouseInfoResponse response = mockHousingController.getHouseInfo(hid);
        assertNotNull(response);
    }

}
