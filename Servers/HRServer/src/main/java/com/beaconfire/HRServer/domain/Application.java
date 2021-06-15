package com.beaconfire.HRServer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ApplicationWorkFlow")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aid")
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    @Column(name = "createDate")
    private String createDate;

    @Column(name = "modificationDate")
    private String modificationDate;

    @Column(name = "status")
    private String status;

    @Column(name = "comments")
    private String comments;

    @Column(name = "applicationType")
    private String applicationType;
}
