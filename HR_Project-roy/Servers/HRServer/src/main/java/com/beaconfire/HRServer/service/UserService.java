package com.beaconfire.HRServer.service;

import com.beaconfire.HRServer.dao.UserDAO;
import com.beaconfire.HRServer.util.DatetimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean passwordIsValid(String password, String retype){
        return password.equals(retype);
    }

    @Transactional
    public boolean userExists(String username){
        return !userDAO.getUserByUsername(username).isEmpty();
    }

    @Transactional
    public Integer addUser(String username, String password, String email){
        String currentDateTime = DatetimeUtil.getCurrentDateTime();
        return userDAO.addUser(username, password, email, currentDateTime);
    }
}
