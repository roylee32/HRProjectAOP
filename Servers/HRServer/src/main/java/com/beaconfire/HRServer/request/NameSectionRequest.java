package com.beaconfire.HRServer.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class NameSectionRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthday;
    private String gender;
    private String eid;
    private String personalEmail;
    private String cellPhone;
    private String workPhone;
    private String workAuth;
    private String workAuthStartDate;
    private String workAuthEndDate;
    private String employmentStartDate;
    private String employmentEndDate;
    private String title;
}
