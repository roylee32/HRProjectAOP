package com.beaconfire.HRServer.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressSectionResponse {

    private String line1;
    private String line2;
    private String city;
    private String state;
    private String zipcode;
    private String errorMessage;
}
