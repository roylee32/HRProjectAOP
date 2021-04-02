package com.beaconfire.HRServer.dao;

import com.beaconfire.HRServer.domain.User;
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

    public List<User> getUserByUsername(String username){
        Session session = sessionFactory.getCurrentSession();
        String statement = "from User where username = :username";
        Query query = session.createQuery(statement);
        query.setParameter("username", username);
        return query.getResultList();
    }

    public Integer addUser(String username, String password, String email, String createDate){
        Session session = sessionFactory.getCurrentSession();
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPword(password);
        newUser.setCreateDate(createDate);
        newUser.setRole("User");
        return (Integer) session.save(newUser);
    }


    public List<User> getUserById1(int uid) {
        Session session = sessionFactory.getCurrentSession();
        String statement = "from User where uid = :uid";
        Query query = session.createQuery(statement);
        query.setParameter("uid", uid);
        return query.getResultList();
    }
    public User getUserById(Integer id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }
}
