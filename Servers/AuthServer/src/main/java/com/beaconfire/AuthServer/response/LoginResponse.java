package com.beaconfire.AuthServer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String errorMessage;
    private String token;
    private String email;

    private String uid;

    private String role;

}
