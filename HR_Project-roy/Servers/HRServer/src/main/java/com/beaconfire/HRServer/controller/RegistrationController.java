package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.request.RegistrationRequest;
import com.beaconfire.HRServer.response.RegistrationResponse;
import com.beaconfire.HRServer.service.TokenService;
import com.beaconfire.HRServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    private UserService userService;
    private TokenService tokenService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/hr/api/registration", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public RegistrationResponse register(@RequestBody RegistrationRequest request){
        RegistrationResponse response = new RegistrationResponse();
        if (!userService.passwordIsValid(request.getPassword(), request.getPasswordRetype())){
            response.setErrorMessage("Passwords do not match.");
            return response;
        } else if (userService.userExists(request.getUsername())) {
            response.setErrorMessage("Username already exists.");
            return response;
        } else if (!tokenService.tokenExists(request.getToken())){
            response.setErrorMessage("Token does not exist.");
            return response;
        } else if (tokenService.tokenExpired(request.getToken())){
            response.setErrorMessage("Token expired.");
            return response;
        }
        String email = tokenService.getAssociatedEmail(request.getToken());
        if (userService.addUser(request.getUsername(), request.getPassword(), email) == null){
            response.setErrorMessage("Failed registering new user.");
        }
        return response;
    }
}
