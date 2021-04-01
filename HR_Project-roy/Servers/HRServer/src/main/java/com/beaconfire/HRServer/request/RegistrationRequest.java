package com.beaconfire.HRServer.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private String token;
    private String username;
    private String password;
    private String passwordRetype;
}
