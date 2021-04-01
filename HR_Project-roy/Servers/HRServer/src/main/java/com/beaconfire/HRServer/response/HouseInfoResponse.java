package com.beaconfire.HRServer.response;

import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.Facility;
import com.beaconfire.HRServer.domain.FacilityReport;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HouseInfoResponse {
    private Integer hid;
    private String address;
    private List<Employee> residents;
    private List<Facility> facilities;
    private List<FacilityReport> facilityReports;
}
