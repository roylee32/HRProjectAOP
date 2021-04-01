package com.beaconfire.HRServer.response;

import com.beaconfire.HRServer.domain.Application;
import com.beaconfire.HRServer.domain.DigitalDocument;
import com.beaconfire.HRServer.domain.PersonalDocument;
import com.beaconfire.HRServer.domain.VisaStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VisaStatusMgmtResponse {

    private VisaStatus visaStatus;
    private Application application;
    private List<PersonalDocument> personalDocuments;
    private String errorMessage;
}
