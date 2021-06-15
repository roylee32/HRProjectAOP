package com.beaconfire.HRServer.response;

import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.domain.VisaStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HrEmployeeProfileResponse {

    @Getter
    @Setter
    @AllArgsConstructor
    class Profile {
        private Employee employeeInfo;
        private VisaStatus visaStatusInfo;
    }

    private List<Profile> profiles = new ArrayList<>();

    public void addProfile(Employee employee, VisaStatus visaStatus) {
        profiles.add(new Profile(employee, visaStatus));
    }
}
