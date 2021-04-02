package com.beaconfire.AuthServer.dao;

import com.beaconfire.AuthServer.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getUserByUsernamePassword(String username, String password){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from User where username = :username and pword = :password";
        Query query = session.createQuery(statement);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getResultList();
    }

    public List<User> getUserByEmailPassword(String email, String password){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from User where email = :email and pword = :password";
        Query query = session.createQuery(statement);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.getResultList();
    }



}
