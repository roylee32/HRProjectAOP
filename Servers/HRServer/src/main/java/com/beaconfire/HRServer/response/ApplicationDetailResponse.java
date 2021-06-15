package com.beaconfire.HRServer.response;

import com.beaconfire.HRServer.domain.Address;
import com.beaconfire.HRServer.domain.Application;
import com.beaconfire.HRServer.domain.Contact;
import com.beaconfire.HRServer.domain.VisaStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationDetailResponse {
    private Application application;
    private List<Address> addresses;
    private List<VisaStatus> visaStatuses;
    private List<Contact> referenceContact;
    private List<Contact> emergenceContact;
    private String avatarURL;
}
