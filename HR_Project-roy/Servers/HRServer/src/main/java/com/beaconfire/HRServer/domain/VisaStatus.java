package com.beaconfire.HRServer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`VisaStatus`")
public class VisaStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vid")
    private int vid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee")
    @JsonIgnore
    private Employee employee;

    @Column(name = "VisaType")
    private String visaType;

    @Column(name = "`active`")
    private boolean active;

    @Column(name = "modificationDate")
    private String modificationDate;

    @Column(name = "VisaStartDate")
    private String visaStartDate;

    @Column(name = "VisaEndDate")
    private String visaEndDate;
}