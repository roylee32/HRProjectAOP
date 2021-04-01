package com.beaconfire.HRServer.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewHouseRequest {
    private String houseAddress;
    private Integer size;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String email;
    private String landlordAddress;
}
