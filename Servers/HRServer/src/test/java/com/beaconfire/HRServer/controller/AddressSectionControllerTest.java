package com.beaconfire.HRServer.controller;


import com.beaconfire.HRServer.domain.Address;
import com.beaconfire.HRServer.response.AddressSectionResponse;
import com.beaconfire.HRServer.service.AddressSectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressSectionControllerTest {

    private AddressSectionService mockAddressSectionService;
    private AddressSectionController addressSectionController;

    @BeforeEach
    public void setup(){
        mockAddressSectionService = mock(AddressSectionService.class);
        addressSectionController = new AddressSectionController();
        addressSectionController.setAddressSectionService(mockAddressSectionService);
        when(mockAddressSectionService.getAddressByEmployeeId(1)).thenReturn(new Address());
    }

    @Test
    public void testGetAddressByUserId(){
        String eid = "1";
        AddressSectionResponse addressSectionResponse = addressSectionController.getAddressByUserId(eid);
        assertNotNull(addressSectionResponse);
    }
}
