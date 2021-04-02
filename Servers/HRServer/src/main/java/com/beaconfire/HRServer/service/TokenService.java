package com.beaconfire.HRServer.service;

import com.beaconfire.HRServer.dao.TokenDAO;
import com.beaconfire.HRServer.util.DatetimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenService {
    TokenDAO tokenDAO;

    @Autowired
    public void setTokenDAO(TokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }

    @Transactional
    public boolean tokenExists(String token){
        return !tokenDAO.getTokenByValue(token).isEmpty();
    }

    @Transactional
    public boolean tokenExpired(String token){
        return DatetimeUtil.isBeforeCurrentDate(tokenDAO.getTokenExpiration(token));
    }

    @Transactional
    public String getAssociatedEmail(String token){
        return tokenDAO.getEmailFromToken(token);
    }

    
}
