package com.beaconfire.HRServer.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacilityReportRequest {
    private Integer hid;
    private String eid;
    private String title;
    private String description;
}
