package com.pomiot.bookkeeper.services;

import com.pomiot.bookkeeper.exceptions.UsernameAlreadyTakenException;
import com.pomiot.bookkeeper.model.Role;
import com.pomiot.bookkeeper.model.User;
import com.pomiot.bookkeeper.model.VerificationToken;
import com.pomiot.bookkeeper.repositories.TokenRepository;
import com.pomiot.bookkeeper.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailingService mailingService;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser(User user) {

        String username = user.getUsername();
        if(userRepository.exists(username)){
            throw new UsernameAlreadyTakenException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        user.setRole(Role.ROLE_USER);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, user);

        userRepository.save(user);
        tokenRepository.save(verificationToken);
        mailingService.sendEmailConfirmationMessage(user, token);

        return user;
    }

    public void verifyUserEmail(String passedToken){
        VerificationToken token = tokenRepository.getByToken(passedToken);
        User user = token.getUser();
        user.setEnabled(true);
        token.setVerified(true);
        userRepository.save(user);
    }
}
