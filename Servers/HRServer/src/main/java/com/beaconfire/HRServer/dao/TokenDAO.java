package com.beaconfire.HRServer.dao;

import com.beaconfire.HRServer.domain.RegistrationToken;
import com.beaconfire.HRServer.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TokenDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<RegistrationToken> getTokenByValue(String token){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from RegistrationToken where token = :token";
        Query query = session.createQuery(statement);
        query.setParameter("token", token);
        return query.getResultList();
    }

    public String getTokenExpiration(String token){
        Session session = sessionFactory.getCurrentSession();
        String statement = "select validDuration from RegistrationToken where token = :token";
        Query query = session.createQuery(statement);
        query.setParameter("token", token);
        return (String) query.getResultList().get(0);
    }

    public String getEmailFromToken(String token){
        Session session = sessionFactory.getCurrentSession();
        String statement = "select email from RegistrationToken where token = :token";
        Query query = session.createQuery(statement);
        query.setParameter("token", token);
        return (String) query.getResultList().get(0);
    }

    public void insertRegistrationToken(String token, String email, String validDuration, User u){
        Session session = sessionFactory.getCurrentSession();

        RegistrationToken newToken = new RegistrationToken();
        newToken.setToken(token);
        newToken.setEmail(email);
        newToken.setValidDuration(validDuration);
        newToken.setCreateBy(u);
        session.save(newToken);
    }
}
