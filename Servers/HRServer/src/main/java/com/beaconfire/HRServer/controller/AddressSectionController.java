package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.domain.Address;
import com.beaconfire.HRServer.request.AddressSectionRequest;
import com.beaconfire.HRServer.response.AddressSectionResponse;
import com.beaconfire.HRServer.service.AddressSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressSectionController {

    AddressSectionService addressSectionService;

    @Autowired
    public void setAddressSectionService(AddressSectionService addressSectionService) {
        this.addressSectionService = addressSectionService;
    }

    @GetMapping(value = "/hr/api/addressSection")
    public AddressSectionResponse getAddressByUserId(@RequestParam String eid) {
        AddressSectionResponse response = new AddressSectionResponse();
        Address address = addressSectionService.getAddressByEmployeeId(Integer.parseInt(eid));
        if (address != null) {
            response.setLine1(address.getAddressLine1());
            response.setLine2(address.getAddressLine2());
            response.setCity(address.getCity());
            response.setState(address.getStateName());
            response.setZipcode(address.getZipcode());
        }
        return response;
    }

    @PostMapping(value = "/hr/api/addressSection", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public AddressSectionResponse updateAddress(@RequestBody AddressSectionRequest request) {
        addressSectionService.updateAddressByUserId(
                Integer.parseInt(request.getEid()), request.getLine1(), request.getLine2(),
                request.getCity(), request.getState(), request.getZipcode());
        AddressSectionResponse response = new AddressSectionResponse();
        response.setLine1(request.getLine1());
        response.setLine2(request.getLine2());
        response.setCity(request.getCity());
        response.setState(request.getState());
        response.setZipcode(request.getZipcode());
        return response;
    }
}
