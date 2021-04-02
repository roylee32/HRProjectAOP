package com.beaconfire.HRServer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "facilityReportDetail")
public class FacilityReportDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "frdid")
    private int frdid;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "report")
    private FacilityReport report;

    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    @Column(name = "comments")
    private String comments;

    @Column(name = "createDate")
    private String createDate;

    @Column(name = "lastModificationDate")
    private String lastModificationDate;
}
