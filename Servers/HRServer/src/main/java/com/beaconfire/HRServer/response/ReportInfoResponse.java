package com.beaconfire.HRServer.response;

import com.beaconfire.HRServer.domain.FacilityReport;
import com.beaconfire.HRServer.domain.FacilityReportDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportInfoResponse {
    private FacilityReport facilityReport;
    private List<FacilityReportDetail> facilityReportDetails;
}
