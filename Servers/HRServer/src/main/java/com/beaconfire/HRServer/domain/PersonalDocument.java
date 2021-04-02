package com.beaconfire.HRServer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "PersonalDocument")
public class PersonalDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    @Column(name = "path")
    private String path;

    @Column(name = "title")
    private String title;

    @Column(name = "comment")
    private String comment;

    @Column(name = "createDate")
    private String createDate;
}
