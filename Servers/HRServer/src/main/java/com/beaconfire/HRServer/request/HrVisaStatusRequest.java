package com.beaconfire.HRServer.request;

import com.beaconfire.HRServer.domain.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HrVisaStatusRequest {
    private String eid;
    private String firstName;
    private String lastName;
    private String email;
    private String nextSteps;
    private String fullName;

}
