package com.beaconfire.HRServer.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationCommentRequest {
    private Integer aid;
    private String comment;
}
