package com.beaconfire.HRServer.security;

import io.jsonwebtoken.Jwts;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;

public class JwtUtil {
    public static String getSubject(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
        String token = com.beaconfire.HRServer.security.CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if (token == null){
            return null;
        }
        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
    }

//    public static String getPayload(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey) {
//        String token = com.beaconfire.HRServer.security.CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
//        if (token == null){
//            return null;
//        }
//        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().get("uid", String.class);
//    }
}
