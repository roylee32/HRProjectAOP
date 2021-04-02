package com.beaconfire.AuthServer.service;

import com.beaconfire.AuthServer.dao.UserDAO;
import com.beaconfire.AuthServer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class UserService {
    UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Transactional
    public User getUserByUsernameOrEmail(String usernameOrEmail, String password) {
        List<User> resByUsername = userDAO.getUserByUsernamePassword(usernameOrEmail, password);
        if (!resByUsername.isEmpty()) {
            return resByUsername.get(0);
        }
        List<User> resByEmail = userDAO.getUserByEmailPassword(usernameOrEmail, password);
        return resByEmail.isEmpty()? null : resByEmail.get(0);
    }

    @Transactional
    public boolean isValidUser(String usernameOrEmail, String password){
        if (userDAO.getUserByUsernamePassword(usernameOrEmail, password).isEmpty() &&
                userDAO.getUserByEmailPassword(usernameOrEmail, password).isEmpty()){
            return false;
        }
        return true;
    }

}
