package com.beaconfire.HRServer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`House`")
public class House implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hid")
    private int hid;

    @ManyToOne
    @JoinColumn(name = "contact")
    private Contact contact;

    @Column(name = "address")
    private String address;

    @Column(name = "numberOfPerson")
    private int numberOfPerson;

    @OneToMany(mappedBy = "house")
    @JsonIgnore
    private List<Employee> residents;

    @OneToMany(mappedBy = "house")
    @JsonIgnore
    private List<Facility> facilities;

    @OneToMany(mappedBy = "house")
    @JsonIgnore
    private List<FacilityReport> facilityReports;
}