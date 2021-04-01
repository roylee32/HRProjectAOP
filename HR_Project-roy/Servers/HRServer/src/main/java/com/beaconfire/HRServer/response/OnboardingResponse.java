package com.beaconfire.HRServer.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OnboardingResponse {
    private String message;
    private Integer eid;
}
