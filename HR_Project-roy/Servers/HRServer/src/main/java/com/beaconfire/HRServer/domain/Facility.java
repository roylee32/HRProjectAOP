package com.beaconfire.HRServer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fid")
    private int fid;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "house")
    private House house;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private Integer quantity;
}
