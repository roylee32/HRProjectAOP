package com.beaconfire.HRServer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="RegistrationToken")
public class RegistrationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private int id;

    @Column(name = "token")
    private String token;

    @Column(name = "validDuration")
    private String validDuration;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "createBy")
    private User createBy;
}
