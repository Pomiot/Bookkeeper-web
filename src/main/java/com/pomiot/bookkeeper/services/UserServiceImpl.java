package com.pomiot.bookkeeper.services;

import com.pomiot.bookkeeper.model.Role;
import com.pomiot.bookkeeper.model.User;
import com.pomiot.bookkeeper.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser(User user) {

        if(userRepository.exists(user.getUsername())){
            //TODO: throw something here!
            return null;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRole(Role.ROLE_USER);

        return userRepository.save(user);
    }


}
