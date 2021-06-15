package com.beaconfire.HRServer.controller;

import com.beaconfire.HRServer.domain.Employee;
import com.beaconfire.HRServer.request.RegistrationRequest;
import com.beaconfire.HRServer.response.NameSectionResponse;
import com.beaconfire.HRServer.response.RegistrationResponse;
import com.beaconfire.HRServer.service.NameSectionService;
import com.beaconfire.HRServer.service.S3Service;
import com.beaconfire.HRServer.service.TokenService;
import com.beaconfire.HRServer.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.tools.jstat.Token;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegistrationControllerTest {
    private UserService mockUserService;
    private TokenService mockTokenService;
    private RegistrationController mockRegistrationController;

    @BeforeEach
    public void setup(){
        mockUserService = mock(UserService.class);
        mockTokenService = mock(TokenService.class);
        mockRegistrationController = new RegistrationController();
        mockRegistrationController.setTokenService(mockTokenService);
        mockRegistrationController.setUserService(mockUserService);
        when(mockTokenService.getAssociatedEmail("test")).thenReturn(new String());
    }

    @Test
    public void testRegister(){
        RegistrationRequest request = new RegistrationRequest();
        request.setPassword("test");
        request.setPasswordRetype("test");
        request.setToken("123123131");
        request.setUsername("test");
        RegistrationResponse response = mockRegistrationController.register(request);
        assertNotNull(response);
    }

}
