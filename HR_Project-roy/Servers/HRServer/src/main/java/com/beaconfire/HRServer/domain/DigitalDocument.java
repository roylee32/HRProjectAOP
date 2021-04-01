package com.beaconfire.HRServer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "DigitalDocument")
public class DigitalDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "did")
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "required")
    private Boolean required;

    @Column(name = "templateLocation")
    private String templateLocation;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;
}
