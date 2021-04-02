package com.beaconfire.HRServer.response;

import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.Facility;
import com.beaconfire.HRServer.domain.FacilityReport;
import com.beaconfire.HRServer.domain.House;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class HrHouseInfoResponse {
    private House house;
    private List<Facility> facilities;
    private List<Employee> residents;
    private List<FacilityReport> facilityReports;
}
