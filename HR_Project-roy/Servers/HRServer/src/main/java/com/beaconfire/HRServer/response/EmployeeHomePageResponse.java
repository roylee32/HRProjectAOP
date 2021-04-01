package com.beaconfire.HRServer.response;

import com.beaconfire.HRServer.domain.Employee;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeHomePageResponse {
    private Employee employee;
}
