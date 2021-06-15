package com.beaconfire.HRServer.config;

import com.beaconfire.HRServer.filter.JwtFilter;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class FilterConfig {
    @Value("${service.auth}")
    private String authService;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter(){
        final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.setInitParameters(Collections.singletonMap("service.auth", authService));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
