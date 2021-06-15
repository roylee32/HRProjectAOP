package com.beaconfire.HRServer.response;

import com.beaconfire.HRServer.domain.Application;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationInfoResponse {
    private Application application;
}
