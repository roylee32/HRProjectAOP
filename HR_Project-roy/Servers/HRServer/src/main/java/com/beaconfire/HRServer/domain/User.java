package com.beaconfire.HRServer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private int uid;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "pword")
    private String pword;

    @Column(name = "createDate")
    private String createDate;

    @Column(name = "modificationDate")
    private String modificationDate;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "createBy")
    private List<RegistrationToken> generatedTokens;

}
