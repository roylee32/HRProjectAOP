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
@Table(name = "`Address`")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aid")
    private int aid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee")
    @JsonIgnore
    private Employee employee;

    @Column(name = "addressLine1")
    private String addressLine1;

    @Column(name = "addressLine2")
    private String addressLine2;

    @Column(name = "City")
    private String city;

    @Column(name = "Zipcode")
    private String zipcode;

    @Column(name = "StateName")
    private String stateName;

    @Column(name = "StateAbbr")
    private String stateAbbr;

}