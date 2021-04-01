package com.beaconfire.HRServer.response;
import java.util.List;

import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.PersonalDocument;
import com.beaconfire.HRServer.domain.VisaStatus;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class HrVisaStatusResponse {
    private List<VisaStatus> visas;
    private List<Employee> employees;
    private List<PersonalDocument> pDocs;
    private String fullName;
    private String nextSteps;
    private String errorMessage;
    private Employee employee;
}
