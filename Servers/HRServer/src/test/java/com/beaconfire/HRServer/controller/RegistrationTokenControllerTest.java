package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.request.RegistrationRequest;
import com.beaconfire.HRServer.request.TokenValidationRequest;
import com.beaconfire.HRServer.response.RegistrationResponse;
import com.beaconfire.HRServer.response.TokenValidationResponse;
import com.beaconfire.HRServer.service.TokenService;
import com.beaconfire.HRServer.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.tools.jstat.Token;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegistrationTokenControllerTest {
    private TokenService mockTokenService;
    private RegistrationTokenController mockRegistrationTokenController;

    @BeforeEach
    public void setup(){

        mockTokenService = mock(TokenService.class);
        mockRegistrationTokenController = new RegistrationTokenController();
        mockRegistrationTokenController.setTokenService(mockTokenService);
        when(mockTokenService.tokenExists("test")).thenReturn(true);
    }

    @Test
    public void testTokenValidation(){
        TokenValidationRequest request = new TokenValidationRequest();
        request.setToken("1232131");
        TokenValidationResponse response = mockRegistrationTokenController.tokenValidation(request);
        assertNotNull(response);
    }
}
