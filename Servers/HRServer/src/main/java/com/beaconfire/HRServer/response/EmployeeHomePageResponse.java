package com.beaconfire.HRServer.response;

import com.beaconfire.HRServer.domain.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeHomePageResponse {
    private Employee employee;
}
