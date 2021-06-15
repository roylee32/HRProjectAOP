package com.beaconfire.HRServer.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="`Employee`")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eid")
    private int eid;

    @OneToOne
    @JoinColumn(name = "uid")
    @JsonIgnore
    private User user;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "email")
    private String email;

    @Column(name = "cellPhone")
    private String cellPhone;

    @Column(name = "alternatePhone")
    private String alternatePhone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "SSN")
    private String SSN;

    @Column(name = "DOB")
    private String DOB;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "managerId")
    @JsonIgnore
    private Employee manager;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    @Column(name = "avartar")
    private String avartar;

    @Column(name = "car")
    private String car;

    @Column(name = "DLNumber")
    private String DLNumber;

    @Column(name = "DLExpiration")
    private String DLExpiration;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "houseId")
    private House house;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Employee> subordinates = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private Set<Address> addresses;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private Set<VisaStatus> visaStatuses;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private Set<Contact> contacts;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private Set<Application> applications;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<PersonalDocument> documents;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<FacilityReport> facilityReports;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<FacilityReportDetail> facilityReportDetails;
}
