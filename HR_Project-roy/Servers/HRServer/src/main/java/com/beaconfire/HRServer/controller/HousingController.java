package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.domain.Contact;
import com.beaconfire.HRServer.domain.House;
import com.beaconfire.HRServer.request.AddNewHouseRequest;
import com.beaconfire.HRServer.request.FacilityReportRequest;
import com.beaconfire.HRServer.request.ReportCommentRequest;
import com.beaconfire.HRServer.request.UpdateReportCommentRequest;
import com.beaconfire.HRServer.response.*;
import com.beaconfire.HRServer.service.ContactService;
import com.beaconfire.HRServer.service.HousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HousingController {
    private HousingService housingService;
    private ContactService contactService;

    @Autowired
    public void setHousingService(HousingService housingService) {
        this.housingService = housingService;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/hr/api/getHouseInfo/{eid}")
    public HouseInfoResponse getHouseInfo(@PathVariable String eid){
        System.out.println(eid);
        HouseInfoResponse response = new HouseInfoResponse();
        House house = housingService.getHouseInfoByEid(eid);
        response.setHid(house.getHid());
        response.setAddress(house.getAddress());
        response.setResidents(housingService.getResidents(house));
        response.setFacilities(housingService.getFacilities(house));
        response.setFacilityReports(housingService.getFacilityReportByEid(eid));
        return response;
    }

    @PostMapping(value = "/hr/api/newFacilityReport", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public FacilityReportResponse createNewFacilityReport(@RequestBody FacilityReportRequest request){
        FacilityReportResponse response = new FacilityReportResponse();
        Integer reportId = housingService.createNewFacilityReport(request.getHid(), request.getEid(), request.getTitle(),
                request.getDescription());
        if (reportId != null){
            response.setMessage("New Report Created.");
        } else {
            response.setMessage("Failed to Create New Report.");
        }
        return response;
    }

    @GetMapping("/hr/api/getReportInfo/{frid}")
    public ReportInfoResponse getReportInfo(@PathVariable String frid){
        ReportInfoResponse response = new ReportInfoResponse();
        response.setFacilityReport(housingService.getFacilityReport(frid));
        response.setFacilityReportDetails(housingService.getFacilityReportDetailByFrid(frid));
        return response;
    }

    @PostMapping(value = "/hr/api/newReportComment", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ReportCommentResponse createNewComment(@RequestBody ReportCommentRequest request){
        ReportCommentResponse response = new ReportCommentResponse();
        Integer frdid = housingService.createNewComment(request.getEid(), request.getReportId(), request.getComment());
        if (frdid != null){
            response.setMessage("New Comment Created.");
        } else {
            response.setMessage("Failed to Create New Comment.");
        }
        return response;
    }

    @PostMapping(value = "/hr/api/updateReportComment", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ReportCommentResponse updateComment(@RequestBody UpdateReportCommentRequest request){
        ReportCommentResponse response = new ReportCommentResponse();
        housingService.updateFacilityReportDetailComment(request.getFrdid(), request.getComment());
        response.setMessage("Update Successfully.");
        return response;
    }

    @GetMapping("/hr/api/getAllHouseInfo")
    public AllHousesInfoResponse getAllHouseInfo(){
        AllHousesInfoResponse response = new AllHousesInfoResponse();
        List<House> houseList = housingService.getAllHouses();
        List<Integer> houseResidentSizeList = new ArrayList<Integer>();
        for (House house : houseList){
            houseResidentSizeList.add(housingService.getResidentSize(house.getHid()));
        }
        response.setHouses(houseList);
        response.setSizeList(houseResidentSizeList);
        return response;
    }

    @PostMapping(value = "hr/api/addNewHouse", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ReportCommentResponse addNewHouse(@RequestBody AddNewHouseRequest request){
        ReportCommentResponse response = new ReportCommentResponse();
        Integer contactId = contactService.addNewLandlord(request.getFirstName(), request.getLastName(),
                request.getMiddleName(), request.getPhoneNumber(), request.getEmail(),
                request.getLandlordAddress(), "Landlord");
        if (contactId == null){
            response.setMessage("Failed to Create New Contact.");
            return response;
        }
        Integer houseId = housingService.addNewHouse(contactId, request.getHouseAddress(), request.getSize());
        if (houseId == null){
            response.setMessage("Failed to Create New House");
            return response;
        }
        response.setMessage("New House Successfully Added.");
        return response;
    }

    @GetMapping(value = "/hr/api/getHouseInfoByHid/{hid}")
    public HrHouseInfoResponse getHouseInfo(@PathVariable Integer hid){
        HrHouseInfoResponse response = new HrHouseInfoResponse();
        House house = housingService.getHouseInfoById(hid);
        response.setHouse(house);
        response.setFacilities(housingService.getFacilities(house));
        response.setResidents(housingService.getResidents(house));
        response.setFacilityReports(housingService.getFacilityReportByHouse(house));
        return response;
    }

}
