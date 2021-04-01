package com.beaconfire.HRServer.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class OnboardingRequest {
    private String uid;
    private String firstName;
    private String lastName;
    private String middleName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String zipcode;
    private String state;
    private String cellPhone;
    private String alternatePhone;
    private String carInfo;
    private String ssn;
    private String birthday;
    private String gender;
    private String isCitizenOrPR;
    private String visaType;
    private String visaStartDate;
    private String visaEndDate;
    private String DL;
    private String DLExpiration;
    private String haveReference;
    private String referenceFirstName;
    private String referenceLastName;
    private String referenceMiddleName;
    private String referencePhone;
    private String referenceAddress;
    private String referenceEmail;
    private String referenceRelationShip;
    private String emergencyFirstName;
    private String emergencyLastName;
    private String emergencyMiddleName;
    private String emergencyPhone;
    private String emergencyEmail;
    private String emergencyRelationShip;
}
