package com.beaconfire.HRServer.controller;



import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.request.NameSectionRequest;
import com.beaconfire.HRServer.response.NameSectionResponse;
import com.beaconfire.HRServer.service.NameSectionService;
import com.beaconfire.HRServer.service.S3Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NameSectionControllerTest {
    private NameSectionService mockNameSectionService;
    private S3Service mockS3Service;
    private NameSectionController mockNameSectionController;


    @BeforeEach
    public void setup(){
        mockNameSectionService = mock(NameSectionService.class);
        mockS3Service = mock(S3Service.class);
        mockNameSectionController = new NameSectionController();
        mockNameSectionController.setNameSectionService(mockNameSectionService);
        mockNameSectionController.setS3Service(mockS3Service);
        when(mockNameSectionService.getEmployeeInfoByEID(1)).thenReturn(new Employee());
    }

    @Test
    public void testGetInitialFormValue(){
        String eid = "1";
        NameSectionResponse response = mockNameSectionController.getInitialFormValue(eid);
        assertNotNull(response);
    }

    @Test
    public void testGetInitialFormValue2(){
        NameSectionRequest request = new NameSectionRequest();
        request.setEid("1");
        request.setFirstName("test");
        request.setLastName("test");
        request.setMiddleName("t");
        request.setBirthday("test/test/test");
        request.setGender("test");
        NameSectionResponse response = mockNameSectionController.getInitialFormValue(request);
        assertNotNull(response);
    }

    @Test
    public void testGetContactInfo(){
        String eid = "1";
        NameSectionResponse response = mockNameSectionController.getContactInfoValue(eid);
        assertNotNull(response);
    }

    @Test
    public void testGetContactInfo2(){
        NameSectionRequest request = new NameSectionRequest();
        request.setEid("1");
        request.setPersonalEmail("test@test.com");
        request.setCellPhone("1800test");
        request.setWorkPhone("1test");
        NameSectionResponse response = mockNameSectionController.getContactInfoValue(request);
        assertNotNull(response);
    }

    @Test
    public void testEmploymentInfo(){
        String eid = "1";
        NameSectionResponse response = mockNameSectionController.getEmploymentInfo(eid);
        assertNotNull(response);
    }

    @Test
    public void testEmploymentInfo2(){
        NameSectionRequest request = new NameSectionRequest();
        request.setWorkAuth("test");
        request.setWorkAuthStartDate("test");
        request.setWorkAuthEndDate("test/02/2020");
        request.setEmploymentEndDate("test/02/2020");
        request.setEmploymentStartDate("test/03/2020");
        request.setTitle("testTitle");
        request.setEid("1");
        NameSectionResponse response = mockNameSectionController.getEmploymentInfo(request);
        assertNotNull(response);
    }

    @Test
    public void testEmergenceContactInfo(){
        String eid = "1";
        NameSectionResponse response = mockNameSectionController.getEmergencyContactInfo(eid);
        assertNotNull(response);
    }

}
