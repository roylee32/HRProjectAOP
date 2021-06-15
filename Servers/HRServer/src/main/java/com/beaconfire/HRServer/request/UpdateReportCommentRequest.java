package com.beaconfire.HRServer.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateReportCommentRequest {
    private Integer frdid;
    private String comment;
}
