package com.beaconfire.HRServer.request;

import com.beaconfire.HRServer.domain.Application;
import com.beaconfire.HRServer.domain.VisaStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisaStatusMgmtRequest {

    private String uid;
    private Application application;
}
