package com.beaconfire.HRServer.response;

import com.beaconfire.HRServer.domain.Application;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllApplicationInfoResponse {
    private List<Application> applications;
}
