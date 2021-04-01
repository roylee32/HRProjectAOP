package com.beaconfire.HRServer.service;
import com.beaconfire.HRServer.dao.TokenDAO;
import com.beaconfire.HRServer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.beaconfire.HRServer.util.DatetimeUtil;
import com.beaconfire.HRServer.dao.UserDAO;

import java.util.UUID;
@Service
public class HrRegistrationService {
    @Value("${spring.mail.username}")
    private String rootEmail;
    TokenDAO tokenDao;

    UserDAO userDao;

    @Autowired
    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    public void setTokenDAO(TokenDAO tokenDao) {this.tokenDao = tokenDao;}


    //Generate unique token (32 characters)
    public String generateToken() {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

    public String generateMsg(String uuid){
        return "Hello,\n \nPlease sign up using this unique token: \n" + uuid + "\n \nBest,\nBeaconFire Team";
    }

    public String generateActionMsg(String fullName, String nextSteps){
        return "Hello " + fullName + ",\n \nPlease take care of the next steps in your onboarding process: \n" + nextSteps + "\n \nBest,\nBeaconFire Team";
    }

    /*For email sending we can use Spring Java Mail interface after adding the dependency. We can
    just call generateToken method and attach it to a boilerplate message for every email.
     */
    public void sendEmail(String to, String subject, String uuid){
        SimpleMailMessage message = new SimpleMailMessage();
        //message.setFrom("royhklee@gmail.com");
        message.setFrom(rootEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(generateMsg(uuid));
        emailSender.send(message);
    }

    public void sendActionEmail(String to, String subject, String fullName, String nextSteps){
        SimpleMailMessage message = new SimpleMailMessage();
        //message.setFrom("royhklee@gmail.com");
        message.setFrom(rootEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(generateActionMsg(fullName, nextSteps));
        emailSender.send(message);
    }

    //Need to update username
    @Transactional
    public void doInsertAndSendEmail(String email){
        String token = generateToken();
        String date = DatetimeUtil.get3hrAddedDate();
        //String date1 = "03-22-2021 06:23:33";
        sendEmail(email, "Beaconfire Sign Up Token Verification", token);
        User user = userDao.getUserByUsername("0o0liver").get(0);
        tokenDao.insertRegistrationToken(token,email,date, user);
    }

}
