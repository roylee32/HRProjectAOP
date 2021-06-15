package com.beaconfire.AuthServer.controller;

import com.beaconfire.AuthServer.constant.Constant;
import com.beaconfire.AuthServer.domain.User;
import com.beaconfire.AuthServer.request.LoginRequest;
import com.beaconfire.AuthServer.response.LoginResponse;
import com.beaconfire.AuthServer.security.CookieUtil;
import com.beaconfire.AuthServer.security.JwtUtil;

import com.beaconfire.AuthServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.beaconfire.AuthServer.domain.User;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/auth/api/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public LoginResponse login(HttpServletResponse httpServletResponse, @RequestBody LoginRequest loginRequest){
        LoginResponse response = new LoginResponse();

        if (!userService.isValidUser(loginRequest.getUsernameOrEmail(), loginRequest.getPassword())){
            response.setErrorMessage("Incorrect Login Credentials");
            return response;
        }
        User validUser = userService.getUserByUsernameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
        String token = JwtUtil.generateToken(Constant.SIGNING_KEY, loginRequest.getUsernameOrEmail());
        response.setUid(String.valueOf(validUser.getUid()));
        response.setEmail(validUser.getEmail());
        response.setToken(token);
        response.setRole(validUser.getRole());

        CookieUtil.create(httpServletResponse, Constant.JWT_TOKEN_COOKIE_NAME, token, false, -1, "localhost");

        return response;


    }
}
