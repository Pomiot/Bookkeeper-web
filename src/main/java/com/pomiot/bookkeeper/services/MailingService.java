package com.pomiot.bookkeeper.services;

import com.pomiot.bookkeeper.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailingService {

    @Value("${email.username}")
    private String username;

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailConfirmationMessage(User user, String token){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(user.getEmail());
        message.setSubject("Welcome to Bookkeeper!");
        message.setText("Hi there, " + user.getUsername() + "! Thanks for registering to Bookkeeper!" +
                " http://localhost:8080/verify?token=" + token);

        mailSender.send(message);
    }
}
