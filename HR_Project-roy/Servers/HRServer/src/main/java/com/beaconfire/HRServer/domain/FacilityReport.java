package com.beaconfire.HRServer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "facilityReport")
public class FacilityReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "frid")
    private int frid;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    @Column(name = "reportDate")
    private String reportDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "house")
    private House house;

    @JsonIgnore
    @OneToMany(mappedBy = "report")
    private List<FacilityReportDetail> facilityReportDetails;
}
