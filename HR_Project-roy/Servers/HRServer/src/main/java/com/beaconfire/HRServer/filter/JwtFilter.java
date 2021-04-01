package com.beaconfire.HRServer.filter;
import com.beaconfire.HRServer.constant.Constant;
import com.beaconfire.HRServer.constant.Constant;
import com.beaconfire.HRServer.security.JwtUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String userInfo = JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY);
        if (userInfo ==  null){
            String authService = this.getFilterConfig().getInitParameter("service.auth");
            httpServletResponse.sendRedirect(authService);
        } else {
            httpServletRequest.setAttribute("userInfo", userInfo);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        List<String> excludedPath = Arrays.asList("/hr/api/registration");
        return excludedPath.contains(request.getRequestURI());
    }

}
