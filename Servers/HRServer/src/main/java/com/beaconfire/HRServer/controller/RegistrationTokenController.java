package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.request.TokenValidationRequest;
import com.beaconfire.HRServer.response.TokenValidationResponse;
import com.beaconfire.HRServer.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationTokenController {
    TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/hr/api/registrationTokenValidation", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TokenValidationResponse tokenValidation(@RequestBody TokenValidationRequest request){
        TokenValidationResponse response = new TokenValidationResponse();
        if (!tokenService.tokenExists(request.getToken())){
            response.setErrorMessage("Token does not exist.");
            return response;
        } else if (tokenService.tokenExpired(request.getToken())){
            response.setErrorMessage("Token expired.");
            return response;
        }

        return response;
    }


}
