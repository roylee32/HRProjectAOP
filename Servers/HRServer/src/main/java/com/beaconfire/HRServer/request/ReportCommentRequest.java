package com.beaconfire.HRServer.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportCommentRequest {
    private String eid;
    private String reportId;
    private String comment;
}
