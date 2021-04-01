package com.beaconfire.HRServer.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "`Contact`")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private int cid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee")
    @JsonIgnore
    private Employee employee;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "title")
    private String title;

    @Column(name = "isReference")
    private boolean isReference;

    @Column(name = "isEmergency")
    private boolean isEmergency;

    @Column(name = "isLandLord")
    private boolean isLandlord;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<House> houseList;
}